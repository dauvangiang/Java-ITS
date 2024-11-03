package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityBillRequestDTO;
import com.dvgiang.electricitybillingsystem.dto.response.BaseResponse;
import com.dvgiang.electricitybillingsystem.service.ElectricityBillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/electricity_bills")
@RequiredArgsConstructor
public class ElectricityBillController {
    private final ElectricityBillService billService;

    @GetMapping("/visitor/electricity_bills/search/{id}")
    public ResponseEntity<Object> getAllBillByCustomerId(@PathVariable Long id) {
        return ResponseEntity.ok(
                BaseResponse.ok(billService.getUnpaidBillsByCustomerId(id))
        );
    }

    @PreAuthorize("hasAuthority('WRITE_BILL')")
    @PostMapping("/technician/electricity_bills/write_electricity_bill")
    public ResponseEntity<Object> writeElectricityBilling(@Valid @RequestBody ElectricityBillRequestDTO requestDTO) {
        return new ResponseEntity<>(
                BaseResponse.ok(billService.writeElectricityBilling(requestDTO)),
                HttpStatus.CREATED
        );
    }
}
