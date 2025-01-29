package com.example.sportsmanager.dto;

import com.example.sportsmanager.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDTO {
    private Long id;
    private String data;
    private String empresaNome;
    private String total;
    private String status;

    // 🔹 Construtor que realmente preenche os campos
    public ReservationResponseDTO(Reservation reservation) {
        this.id = reservation.getId(); // Supondo que User tem getNome()
        this.data = String.valueOf(reservation.getData()); // Supondo que Quadra tem getNome()
        this.total = String.valueOf(reservation.getValor_total()); // Se empresa estiver dentro de quadra
        this.status = reservation.getStatus();
        this.empresaNome = reservation.getAvailability().getCourt().getEnterprise().getNome();
    }
}
