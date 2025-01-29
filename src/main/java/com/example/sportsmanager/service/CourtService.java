package com.example.sportsmanager.service;
import java.util.List;

import com.example.sportsmanager.model.Court;
import com.example.sportsmanager.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourtService {
    @Autowired
    private final CourtRepository courtRepository;

    public CourtService(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    public List<Court> listAllCourts() {
        return courtRepository.findAll();
    }
}
