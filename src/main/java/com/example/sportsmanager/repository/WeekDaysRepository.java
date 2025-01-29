package com.example.sportsmanager.repository;

import com.example.sportsmanager.model.Enterprise;
import com.example.sportsmanager.model.WeekDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sportsmanager.model.Court;

@Repository
public interface WeekDaysRepository extends JpaRepository<WeekDays, Long> {

}
