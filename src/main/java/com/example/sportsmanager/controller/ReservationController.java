package com.example.sportsmanager.controller;

import com.example.sportsmanager.dto.ReservationRequestDTO;
import com.example.sportsmanager.dto.ReservationResponseDTO;
import com.example.sportsmanager.infra.security.CustomUserDetailsService;
import com.example.sportsmanager.infra.security.TokenService;
import com.example.sportsmanager.model.Availability;
import com.example.sportsmanager.model.Prices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;
import com.example.sportsmanager.model.Reservation;
import com.example.sportsmanager.model.User;
import com.example.sportsmanager.repository.AvailabilityRepository;
import com.example.sportsmanager.repository.ReservationRepository;
import com.example.sportsmanager.repository.PricesRepository;
import com.example.sportsmanager.repository.UserRepository;
import com.example.sportsmanager.service.ReservationService;
import com.example.sportsmanager.service.AvailabilityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);


    @Autowired
    private ReservationService reservationService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> criarReserva(@RequestBody Map<String, Object> dadosReserva, HttpServletRequest request) {
        logger.info("Dados da reserva recebidos: {}", dadosReserva);

        Long precoId = Long.parseLong(dadosReserva.get("precoId").toString());
        String dataStr = dadosReserva.get("data").toString();
        LocalDateTime dataReserva;

        Long quadraId = Long.parseLong(dadosReserva.get("quadraId").toString());

        try {
            dataReserva = LocalDateTime.parse(dataStr);
        } catch (DateTimeParseException e) {
            dataReserva = LocalDate.parse(dataStr).atStartOfDay();
        }


        String token = request.getHeader("Authorization").substring(7);
        String userEmail = tokenService.validateToken(token);
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));


        Availability availability = availabilityRepository.findById(precoId)
                .orElseThrow(() -> new RuntimeException("Disponibilidade não encontrada"));

        float price = availability.getPrices().getPrice();

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setAvailability(availability);
        reservation.setData(LocalDate.from(dataReserva));
        reservation.setValor_total(BigDecimal.valueOf(price));
        reservation.setStatus("pendente");

        Reservation savedReservation = reservationService.save(reservation);

        ReservationResponseDTO responseDTO = new ReservationResponseDTO(savedReservation);

        return ResponseEntity.ok(responseDTO);
    }


    // Buscar uma reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> buscarReserva(@PathVariable Long id) {
        ReservationResponseDTO reserva = reservationService.buscarReservaPorId(id);
        return ResponseEntity.ok(reserva);
    }

    @GetMapping("/listByUser")
    public ResponseEntity<List<ReservationResponseDTO>> buscarPorUsuario(HttpServletRequest request) {
        // Obtém o token da requisição
        String token = request.getHeader("Authorization").substring(7);  // Remove o prefixo "Bearer " do token
        String userEmail = tokenService.validateToken(token);  // Valida o token e extrai o e-mail do usuário

        // Busca o usuário pelo e-mail
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Busca as reservas do usuário
        List<ReservationResponseDTO> reservas = reservationService.buscarReservasPorUsuarioId(user.getId());

        try {
            // Converte a lista de reservas em JSON para facilitar a visualização no log
            String reservasJson = objectMapper.writeValueAsString(reservas);
            System.out.println(reservasJson);  // Logando as reservas como JSON
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Retorna as reservas com sucesso
        return ResponseEntity.ok(reservas);
    }


    // Listar todas as reservas
    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> listarReservas() {
        List<ReservationResponseDTO> reservas = reservationService.listarTodas();
        return ResponseEntity.ok(reservas);
    }

    // Cancelar uma reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // Retorna 401 se o token estiver ausente ou mal formatado
        }

        String token = authHeader.substring(7);
        String userEmail = tokenService.validateToken(token);

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        reservationService.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }

}

