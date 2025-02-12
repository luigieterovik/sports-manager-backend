package com.example.sportsmanager.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "periodo_disponibilidade")
public class Periods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime horario_inicio;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime horario_fim;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getHorario_inicio() {
        return horario_inicio;
    }

    public void setHorario_inicio(LocalTime horario_inicio) {
        this.horario_inicio = horario_inicio;
    }

    public LocalTime getHorario_fim() {
        return horario_fim;
    }

    public void setHorario_fim(LocalTime horario_fim) {
        this.horario_fim = horario_fim;
    }
}
