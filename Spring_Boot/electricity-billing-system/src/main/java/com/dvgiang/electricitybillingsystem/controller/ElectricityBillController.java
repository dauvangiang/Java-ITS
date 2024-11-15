package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityBillDTO;
import com.dvgiang.electricitybillingsystem.dto.response.BaseResponse;
import com.dvgiang.electricitybillingsystem.service.electricitybill.ElectricityBillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ElectricityBillController {
    private final ElectricityBillService billService;

    @GetMapping("/visitor/electricity_bills/search/{id}")
    public ResponseEntity<Object> getAllBillByCustomerId(@PathVariable Long id) {
        return ResponseEntity.ok(
                BaseResponse.ok(billService.getUnpaidBillsByCustomerId(id))
        );
    }

    @PostMapping("/technician/electricity_bills/write_electricity_bill")
    public ResponseEntity<Object> writeElectricityBilling(@Valid @RequestBody ElectricityBillDTO requestDTO) {
        return new ResponseEntity<>(
                BaseResponse.ok(billService.writeElectricityBilling(requestDTO)),
                HttpStatus.CREATED
        );
    }
}
