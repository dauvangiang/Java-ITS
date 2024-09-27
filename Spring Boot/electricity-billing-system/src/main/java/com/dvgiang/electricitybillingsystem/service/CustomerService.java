package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.model.Customer;
import com.dvgiang.electricitybillingsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    //Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    //Get customer by id
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    //Create new customer
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    //Delete customer by id
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    //Update customer info
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public boolean isExitsCustomer(Long id) {
        return customerRepository.existsById(id);
    }
}
