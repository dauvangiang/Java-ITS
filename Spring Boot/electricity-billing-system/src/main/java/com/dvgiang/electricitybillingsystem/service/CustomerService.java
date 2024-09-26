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
        return customerRepository.fillAll();
    }

    //Get customer by id
    public Customer getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    //Create new customer
    public boolean createCustomer(Customer customer) {
        return customerRepository.addCustomer(customer);
    }

    //Delete customer by id
    public boolean deleteCustomer(int id) {
        return customerRepository.deleteCustomer(id);
    }

    //Update customer infor
    public boolean updateCustomer(Customer customer) {
        return customerRepository.updateCustomer(customer);
    }
}
