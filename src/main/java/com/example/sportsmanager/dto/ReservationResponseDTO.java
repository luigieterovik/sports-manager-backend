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
    private String courtType;
    private String uf;
    private String city;
    private String neighborhood;
    private String street;
    private String number;
    private String complement;

    public ReservationResponseDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.data = String.valueOf(reservation.getData());
        this.total = String.valueOf(reservation.getValor_total());
        this.status = reservation.getStatus();
        this.empresaNome = reservation.getAvailability().getCourt().getEnterprise().getNome();
        this.courtType = reservation.getAvailability().getCourt().getTipo();
        this.uf = reservation.getAvailability().getCourt().getUf();
        this.city = reservation.getAvailability().getCourt().getCidade();
        this.neighborhood = reservation.getAvailability().getCourt().getBairro();
        this.street = reservation.getAvailability().getCourt().getLogradouro();
        this.number = reservation.getAvailability().getCourt().getNumero();
        this.complement = reservation.getAvailability().getCourt().getComplemento();
    }
}
