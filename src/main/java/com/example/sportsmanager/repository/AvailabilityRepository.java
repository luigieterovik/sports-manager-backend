package com.example.sportsmanager.repository;

import com.example.sportsmanager.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sportsmanager.model.Court;

import java.util.Optional;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    Optional<Availability> findFirstByCourt_Id(Long courtId);  // Use 'court.id' ao invés de 'quadraId'

    Optional<Availability> findById(Long id);

    Optional<Availability> findByCourt_IdAndPrices_Id(Long courtId, Long priceId);
}
