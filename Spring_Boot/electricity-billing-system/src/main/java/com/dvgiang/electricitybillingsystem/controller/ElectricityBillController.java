package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityBillRequestDTO;
import com.dvgiang.electricitybillingsystem.dto.response.SuccessResponseDTO;
import com.dvgiang.electricitybillingsystem.entity.ElectricityBill;
import com.dvgiang.electricitybillingsystem.service.ElectricityBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/electricity_bills")
@RequiredArgsConstructor
public class ElectricityBillController {
  private final ElectricityBillService billService;

  //Tra cuu hoa don bang ma KH
  @GetMapping("/visitor/electricity_bills/search/{id}")
  public ResponseEntity<Object> getAllBillByCustomerId(@PathVariable Long id) {
    List<ElectricityBill> listBill = billService.getAllBillUnpaidByCustomerId(id);
    SuccessResponseDTO successResponseDTO = SuccessResponseDTO
            .builder()
            .data(listBill)
            .status(HttpStatus.OK)
            .statusCode(200)
            .build();
    return ResponseEntity.ok(successResponseDTO);
  }

  //Ghi so dien
  @PreAuthorize("hasAuthority('WRITE_BILL')")
  @PostMapping("/technician/electricity_bills/write_electricity_bill")
  public ResponseEntity<Object> writeElectricityBilling(@RequestBody ElectricityBillRequestDTO requestDTO) {
    ElectricityBill bill = billService.writeElectricityBilling(requestDTO);
    SuccessResponseDTO successResponseDTO = SuccessResponseDTO
            .builder()
            .data(bill)
            .status(HttpStatus.CREATED)
            .statusCode(201)
            .build();
    return new ResponseEntity<>(successResponseDTO, HttpStatus.CREATED);
  }
}