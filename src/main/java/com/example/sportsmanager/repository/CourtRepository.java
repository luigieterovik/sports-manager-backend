package com.example.sportsmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sportsmanager.model.Court;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {

}

