package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityBillRequestDTO;
import com.dvgiang.electricitybillingsystem.response.ResponseHandler;
import com.dvgiang.electricitybillingsystem.service.ElectricityBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/electricity_bills")
@RequiredArgsConstructor
public class ElectricityBillController {
  private final ElectricityBillService billService;

  //Tra cuu hoa don bang ma KH
  @GetMapping("/visitor/electricity_bills/search/{customerId}")
  public ResponseEntity<Object> getAllBillByCustomerId(@PathVariable Long customerId) {
    return ResponseHandler.responseBuilder(billService.getAllBillByCustomerId(customerId), HttpStatus.OK, null, null);
  }

  //Ghi so dien
  @PostMapping("/technician/electricity_bills/write_electricity_bill")
  public ResponseEntity<Object> writeElectricityBilling(@RequestBody ElectricityBillRequestDTO requestDTO) {
    return ResponseEntity.ok(billService.writeElectricityBilling(requestDTO));
  }
}
