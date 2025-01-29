package com.example.sportsmanager.controller;

import com.example.sportsmanager.model.Prices;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sportsmanager.service.PricesService;

@RestController
@RequestMapping("/prices")
public class PriceController {
    private final PricesService priceService;

    public PriceController(PricesService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/findAll")
    public List<Prices> findAll() {
        return priceService.listAllPrices();
    }
}
