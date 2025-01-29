package com.example.sportsmanager.controller;

import com.example.sportsmanager.model.Court;
import com.example.sportsmanager.repository.CourtRepository;
import com.example.sportsmanager.service.CourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courts")
public class CourtController {
    private final CourtService courtService;

    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping("/findAll")
    public List<Court> findAll(){
        return courtService.listAllCourts();
    }
}
