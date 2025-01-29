package com.example.sportsmanager.service;

import com.example.sportsmanager.controller.ReservationController;
import com.example.sportsmanager.model.Availability;
import com.example.sportsmanager.repository.AvailabilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


import java.util.Optional;

@Service
public class AvailabilityService {
    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public Optional<Availability> buscarDisponibilidadePorQuadraEPreco(Long quadraId, Long precoId) {
        logger.info("Buscando disponibilidade para QuadraID: {}, PrecoID: {}", quadraId, precoId);
        Optional<Availability> availability = availabilityRepository.findByCourt_IdAndPrices_Id(quadraId, precoId);
        if (availability.isPresent()) {
            logger.info("Disponibilidade encontrada: {}", availability.get());
        } else {
            logger.warn("Nenhuma disponibilidade encontrada para QuadraID: {} e PrecoID: {}", quadraId, precoId);
        }
        return availability;
    }


}
