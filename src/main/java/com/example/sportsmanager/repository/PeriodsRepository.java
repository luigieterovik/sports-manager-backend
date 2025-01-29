package com.example.sportsmanager.repository;

import com.example.sportsmanager.model.Enterprise;
import com.example.sportsmanager.model.Periods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sportsmanager.model.Court;

@Repository
public interface PeriodsRepository extends JpaRepository<Periods, Long> {

}
