package com.example.sportsmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "preco_disponibilidade")
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "periodo_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Periods period;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dia_semana", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private WeekDays weekDay;

    @Column(name = "valor", nullable = false)
    private float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Periods getPeriod() {
        return period;
    }

    public void setPeriod(Periods period) {
        this.period = period;
    }

    public WeekDays getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDays weekDay) {
        this.weekDay = weekDay;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
