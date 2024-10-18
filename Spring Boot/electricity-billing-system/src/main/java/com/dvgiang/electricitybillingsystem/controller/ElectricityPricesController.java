package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityPricesRequestDTO;
import com.dvgiang.electricitybillingsystem.dto.response.Response;
import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import com.dvgiang.electricitybillingsystem.dto.response.ResponseHandler;
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
        Response response = Response
                .builder()
                .data(listElectricityPrices)
                .status(HttpStatus.OK)
                .statusCode(200)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getElectricityPriceById(@PathVariable Long id) {
        ElectricityPrices electricityPrices = electricityPricesService.getElectricityPriceById(id);
        Response response = Response
                .builder()
                .data(electricityPrices)
                .status(HttpStatus.OK)
                .statusCode(200)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createElectricityPrices(@Valid @RequestBody ElectricityPricesRequestDTO electricityPricesRequestDTO) {
        ElectricityPrices electricityPrices = electricityPricesService.createElectricityPrices(electricityPricesRequestDTO);
        Response response = Response
                .builder()
                .data(electricityPrices)
                .status(HttpStatus.CREATED)
                .statusCode(201)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateElectricityPrices(@Valid @RequestBody ElectricityPricesRequestDTO electricityPricesRequestDTO) {
        ElectricityPrices electricityPrices = electricityPricesService.updateElectricityPrices(electricityPricesRequestDTO);
        Response response = Response
                .builder()
                .data(electricityPrices)
                .status(HttpStatus.OK)
                .statusCode(200)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/delete/{id}")
    public String deleteElectricityPricesById(@PathVariable Long id) {
        return electricityPricesService.deleteElectricityPricesById(id);
    }
}
