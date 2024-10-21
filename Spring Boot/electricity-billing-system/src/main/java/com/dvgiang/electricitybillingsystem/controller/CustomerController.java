package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.CustomerDTO;
import com.dvgiang.electricitybillingsystem.dto.response.SuccessResponseDTO;
import com.dvgiang.electricitybillingsystem.entity.Customer;
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
        SuccessResponseDTO successResponseDTO = SuccessResponseDTO
                .builder()
                .data(listCustomer)
                .status(HttpStatus.OK)
                .statusCode(200)
                .build();
        return ResponseEntity.ok(successResponseDTO);
    }

    @GetMapping("/{id}")
    //@PathVariable: ánh xạ biến đường dẫn đến tham số id của phương thức
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);

        //Custom builder
//        ResponseBuilder builder = new ResponseBuilder();
//        Response response = builder
//                .data(customer)
//                .status(HttpStatus.OK)
//                .statusCode(HttpStatus.OK.value())
//                .headers(headers)
//                .build();

        //Builder lombok
        SuccessResponseDTO successResponseDTO = SuccessResponseDTO
                .builder()
                .data(customer)
                .status(HttpStatus.OK)
                .statusCode(200)
                .build();

        return ResponseEntity.ok(successResponseDTO);
    }

    @PostMapping("create")
    public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.createCustomer(customerDTO);
        SuccessResponseDTO successResponseDTO = SuccessResponseDTO
                .builder()
                .data(customer)
                .status(HttpStatus.CREATED)
                .statusCode(201)
                .build();
        return new ResponseEntity<>(successResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomerById(@PathVariable Long id) {
        return customerService.deleteCustomerByID(id);
    }

    //Update customer info
    @PostMapping("/update")
    public ResponseEntity<Object> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.updateCustomer(customerDTO);
        SuccessResponseDTO successResponseDTO = SuccessResponseDTO
                .builder()
                .data(customer)
                .status(HttpStatus.OK)
                .statusCode(200)
                .build();
        return ResponseEntity.ok(successResponseDTO);
    }
}
