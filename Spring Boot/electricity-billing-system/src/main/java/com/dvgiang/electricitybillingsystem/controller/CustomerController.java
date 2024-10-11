package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.CustomerDTO;
import com.dvgiang.electricitybillingsystem.model.Customer;
import com.dvgiang.electricitybillingsystem.response.ResponseHandler;
import com.dvgiang.electricitybillingsystem.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("admin/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping()
    public ResponseEntity<Object> getAllCustomers() {
        List<Customer> listCustomer = customerService.getAllCustomers();
        return ResponseHandler.responseBuilder(listCustomer, HttpStatus.OK, null,null);
    }

    @GetMapping("/{id}")
    //@PathVariable: ánh xạ biến đường dẫn đến tham số id của phương thức
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseHandler.responseBuilder(customer , HttpStatus.OK, null,null);
    }

    @PostMapping("create")
    public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.createCustomer(customerDTO);
        return ResponseHandler.responseBuilder(customer , HttpStatus.OK, null,null);
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

    //Update customer info
    @PostMapping("/update")
    public ResponseEntity<Object> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.updateCustomer(customerDTO);
        return ResponseHandler.responseBuilder(customer , HttpStatus.OK, null,null);
    }
}
