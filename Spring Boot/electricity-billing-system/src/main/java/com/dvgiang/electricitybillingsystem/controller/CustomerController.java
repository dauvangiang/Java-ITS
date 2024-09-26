package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.model.Customer;
import com.dvgiang.electricitybillingsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    //Auto-inject an object of CustomerService class
    @Autowired
    private CustomerService customerService;

    // Get all customers
    @GetMapping()
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    //Get customer by id
    @GetMapping("/{id}")
    //ResponseEntity: represents for HTTP response
    //@PathVariable: map the path variable to the method's parameter (id)
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer); //ok(customer): return 200 OK, the body of the response is customer
        }
        //status(HttpStatus.NOT_FOUND): 404 Not Found
        //build(): response has no body
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //Create new customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        boolean created = customerService.createCustomer(customer);
        if (created) {
            //status(HttpStatus.CREATED): 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(customer);
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    //Delete customer by id
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        if (customerService.deleteCustomer(id)) {
            return ResponseEntity.ok("Deleted successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        if (customerService.updateCustomer(customer)) {
            return ResponseEntity.ok("Updated successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
