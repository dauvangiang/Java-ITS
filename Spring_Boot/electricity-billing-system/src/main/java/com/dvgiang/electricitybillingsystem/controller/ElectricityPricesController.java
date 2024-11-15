package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityPricesDTO;
import com.dvgiang.electricitybillingsystem.dto.response.BaseResponse;
import com.dvgiang.electricitybillingsystem.service.electricityprices.ElectricityPricesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/electricity_prices")
@RequiredArgsConstructor
public class ElectricityPricesController {
    private final ElectricityPricesService electricityPricesService;

    @GetMapping
    public ResponseEntity<Object> getAllElectricityPrices() {
        return ResponseEntity.ok(BaseResponse.ok(
                electricityPricesService.getAllElectricityPrices(false, null)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getElectricityPriceById(@PathVariable Long id) {
        return ResponseEntity.ok(
                BaseResponse.ok(electricityPricesService.getElectricityPriceById(id))
        );
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createElectricityPrices(@Valid @RequestBody ElectricityPricesDTO electricityPricesDTO) {
        return new ResponseEntity<>(
                BaseResponse.ok(electricityPricesService.createElectricityPrices(electricityPricesDTO)),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateElectricityPrices(@Valid @RequestBody ElectricityPricesDTO electricityPricesDTO) {
        return ResponseEntity.ok(
                BaseResponse.ok(electricityPricesService.updateElectricityPrices(electricityPricesDTO))
        );
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteElectricityPricesById(@PathVariable Long id) {
        return ResponseEntity.ok(
                BaseResponse.ok(electricityPricesService.deleteElectricityPricesById(id))
        );
    }
}
