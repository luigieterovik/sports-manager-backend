package com.example.sportsmanager.service;

import com.example.sportsmanager.dto.ReservationRequestDTO;
import com.example.sportsmanager.dto.ReservationResponseDTO;
import com.example.sportsmanager.model.Availability;
import com.example.sportsmanager.model.Reservation;
import com.example.sportsmanager.model.User;
import com.example.sportsmanager.repository.AvailabilityRepository;
import com.example.sportsmanager.repository.ReservationRepository;
import com.example.sportsmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public ReservationResponseDTO criarReserva(ReservationRequestDTO dto) {
        User user = userRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Availability availability = availabilityRepository.findById(dto.getDisponibilidadeId())
                .orElseThrow(() -> new RuntimeException("Availability não encontrada"));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setAvailability(availability);
        reservation.setData(LocalDate.from(dto.getDataReserva()));
        reservation.setStatus("Pendente"); // Aqui estamos preenchendo o status

        reservation = reservationRepository.save(reservation);

        return new ReservationResponseDTO(
                reservation
        );
    }

    public ReservationResponseDTO buscarReservaPorId(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        return new ReservationResponseDTO(reservation);
    }

    public List<ReservationResponseDTO> buscarReservasPorUsuarioId(Long userId) {
        List<Reservation> reservas = reservationRepository.findByUserId(userId);

        return reservas.stream()
                .map(ReservationResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Listar todas as reservas
    public List<ReservationResponseDTO> listarTodas() {
        List<Reservation> reservas = reservationRepository.findAll();
        return reservas.stream()
                .map(ReservationResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Cancelar reserva
    public void cancelarReserva(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new RuntimeException("Reserva não encontrada");
        }
        reservationRepository.deleteById(id);
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

}
