package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityBillRequestDTO;
import com.dvgiang.electricitybillingsystem.service.ElectricityBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/electricity_bills")
@RequiredArgsConstructor
public class ElectricityBillController {
  private final ElectricityBillService billService;

  //Tra cuu hoa don bang ma KH
  @GetMapping("/search/{customerId}")
  public ResponseEntity<Object> getBillByCustomerId(@PathVariable Long customerId) {
    return ResponseEntity.ok(billService.getBillByCustomerId(customerId));
  }

  //Ghi so dien
  @PostMapping("/write_electri_bill")
  public ResponseEntity<Object> writeElectricityBilling(@RequestBody ElectricityBillRequestDTO requestDTO) {
    return ResponseEntity.ok(billService.writeElectricityBilling(requestDTO));
  }
}
