package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityPricesRequestDTO;
import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import com.dvgiang.electricitybillingsystem.response.ResponseHandler;
import com.dvgiang.electricitybillingsystem.service.ElectricityPricesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/electricity_prices")
@RequiredArgsConstructor
public class ElectricityPricesController {
    private final ElectricityPricesService electricityPricesService;

    @GetMapping
    public ResponseEntity<Object> getAllElectricityPrices() {
        List<ElectricityPrices> listElectricityPrices = electricityPricesService.getAllElectricityPrices();
        return ResponseHandler.responseBuilder(listElectricityPrices, HttpStatus.OK, null, null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getElectricityPriceById(@PathVariable Long id) {
        ElectricityPrices electricityPrices = electricityPricesService.getElectricityPriceById(id);
        return ResponseHandler.responseBuilder(electricityPrices, HttpStatus.OK, null, null);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createElectricityPrices(@Valid @RequestBody ElectricityPricesRequestDTO electricityPricesRequestDTO) {
        ElectricityPrices electricityPrices = electricityPricesService.createElectricityPrices(electricityPricesRequestDTO);
        return ResponseHandler.responseBuilder(electricityPrices, HttpStatus.OK, null, null);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateElectricityPrices(@Valid @RequestBody ElectricityPricesRequestDTO electricityPricesRequestDTO) {
        ElectricityPrices electricityPrices = electricityPricesService.updateElectricityPrices(electricityPricesRequestDTO);
        return ResponseHandler.responseBuilder(electricityPrices, HttpStatus.OK, null, null);
    }

    @GetMapping("/delete/{id}")
    public String deleteElectricityPricesById(@PathVariable Long id) {
        return electricityPricesService.deleteElectricityPricesById(id);
    }
}
