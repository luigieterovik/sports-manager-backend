package com.example.sportsmanager.service;

import com.example.sportsmanager.model.Prices;
import com.example.sportsmanager.repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricesService {
    @Autowired
    private final PricesRepository pricesRepository;

    public PricesService(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    public List<Prices> listAllPrices() {
        return pricesRepository.findAll();
    }
}
