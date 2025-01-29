package com.example.sportsmanager.service;

import com.example.sportsmanager.model.Availability;
import com.example.sportsmanager.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public Long buscarPrimeiraDisponibilidadePorQuadra(Long quadraId) {
        return availabilityRepository.findFirstByCourt_IdOrderByIdAsc(quadraId)
                .map(Availability::getId) // Retorna apenas o ID da disponibilidade encontrada
                .orElse(null); // Retorna null se não encontrar
    }
}
