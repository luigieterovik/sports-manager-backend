package com.example.sportsmanager.repository;

import com.example.sportsmanager.model.Enterprise;
import com.example.sportsmanager.model.Reservation;
import com.example.sportsmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sportsmanager.model.Court;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);

    List<Reservation> findByUserId(Long userId);
}
