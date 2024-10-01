package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.CustomerDTO;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.model.Customer;
import com.dvgiang.electricitybillingsystem.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    //Get customer by id
    public Customer getCustomerById(Long id) {
        //Nếu không có bản ghi phù hợp, ném ra ngoại lệ: Not Found
        if (customerRepository.findById(id).isEmpty()) {
            throw new NotFoundException("The requested customer does not exist!");
        }
        return customerRepository.findById(id).get();
    }

    //Create new customer
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO.getName(), customerDTO.getPhone(), customerDTO.getAddress());
        return customerRepository.save(customer);
    }

    //Delete customer by id
    public void deleteCustomer(Long id) {
//        throw new NotFoundException("Khong duoc xoa");
        customerRepository.deleteById(id);
    }

    //Update customer info
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
