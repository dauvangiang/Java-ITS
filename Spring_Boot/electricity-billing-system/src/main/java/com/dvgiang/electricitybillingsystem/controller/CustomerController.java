package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.CustomerDTO;
import com.dvgiang.electricitybillingsystem.dto.response.BaseResponse;
import com.dvgiang.electricitybillingsystem.service.customer.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("admin/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/page/{page}")
    public ResponseEntity<Object> getCustomersByPage(@PathVariable int page) {
        return ResponseEntity.ok(
                BaseResponse.ok(customerService.getCustomersByPage(page, 5))
        );
    }

    @GetMapping()
    public ResponseEntity<Object> getAllCustomers() {
        return ResponseEntity.ok(
                BaseResponse.ok(customerService.getCustomers())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(
                BaseResponse.ok(customerService.getCustomerById(id, true))
        );
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(
                BaseResponse.ok(customerService.createCustomer(customerDTO)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.ok(customerService.deleteCustomerByID(id)));
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(
                BaseResponse.ok(customerService.updateCustomer(customerDTO))
        );
    }
}
