package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityPricesRequestDTO;
import com.dvgiang.electricitybillingsystem.model.Configuration;
import com.dvgiang.electricitybillingsystem.response.ResponseHandler;
import com.dvgiang.electricitybillingsystem.service.ConfigurationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/electricity_prices")
@RequiredArgsConstructor
public class ElectricityPricesController {
    private final ConfigurationService electricityPricesService;

    @GetMapping
    public ResponseEntity<Object> getAllConfigurations() {
        List<Configuration> listConfiguration = electricityPricesService.getAllConfigurations();
        return ResponseHandler.responseBuilder(listConfiguration, HttpStatus.OK, null, null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getConfigById(@PathVariable Long id) {
        Configuration configuration = electricityPricesService.getConfigById(id);
        return ResponseHandler.responseBuilder(configuration, HttpStatus.OK, null, null);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createConfig(@Valid @RequestBody ElectricityPricesRequestDTO electricityPricesRequestDTO) {
        Configuration configuration = electricityPricesService.createConfig(electricityPricesRequestDTO);
        return ResponseHandler.responseBuilder(configuration, HttpStatus.OK, null, null);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateConfig(@Valid @RequestBody ElectricityPricesRequestDTO electricityPricesRequestDTO) {
        Configuration configuration = electricityPricesService.updateConfig(electricityPricesRequestDTO);
        return ResponseHandler.responseBuilder(configuration, HttpStatus.OK, null, null);
    }

    @GetMapping("/delete/{id}")
    public String deleteConfigById(@PathVariable Long id) {
        return electricityPricesService.deleteConfigById(id);
    }
}
