package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.CustomerDTO;
import com.dvgiang.electricitybillingsystem.dto.response.BaseResponse;
import com.dvgiang.electricitybillingsystem.service.customer.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("admin/customers")
public class CustomerController {
//    private final CustomerService customerService;
    private final CustomerService customerService;

    @PreAuthorize("hasAuthority('READ_CUSTOMERS')")
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> getCustomersByPage(@PathVariable int page) {
        return ResponseEntity.ok(
                BaseResponse.ok(customerService.getCustomersByPage(page, 5))
        );
    }

    @PreAuthorize("hasAuthority('READ_CUSTOMERS')")
    @GetMapping()
    public ResponseEntity<Object> getAllCustomers() {
        return ResponseEntity.ok(
                BaseResponse.ok(customerService.getCustomers())
        );
    }

    @PreAuthorize("hasAuthority('READ_CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(
                BaseResponse.ok(customerService.getCustomerById(id))
        );
    }

    @PreAuthorize("hasAuthority('WRITE_CUSTOMER')")
    @PostMapping("create")
    public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(
                BaseResponse.ok(customerService.createCustomer(customerDTO)),
                HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasAuthority('DELETE_CUSTOMER')")
    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.ok(customerService.deleteCustomerByID(id)));
    }

    @PreAuthorize("hasAuthority('UPDATE_CUSTOMER')")
    @PostMapping("/update")
    public ResponseEntity<Object> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(
                BaseResponse.ok(customerService.updateCustomer(customerDTO))
        );
    }
}
