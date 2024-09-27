package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.model.Customer;
import com.dvgiang.electricitybillingsystem.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //Get all customers
    @GetMapping()
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    //Get customer by id
    @GetMapping("/{id}")
    //Optional: nullable
    //@PathVariable: map the path variable to the method's parameter (id)
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    //Create new customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    //Delete customer by id
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "Deleted successfully!";
    }

    //Update customer info
    @PostMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }
}
