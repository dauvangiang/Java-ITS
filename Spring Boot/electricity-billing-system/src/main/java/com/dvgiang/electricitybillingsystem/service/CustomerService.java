package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.CustomerDTO;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.model.Configuration;
import com.dvgiang.electricitybillingsystem.model.Customer;
import com.dvgiang.electricitybillingsystem.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        //Nếu không có bản ghi phù hợp, ném ra ngoại lệ: Not Found
        if (customer.isEmpty()) {
            throw new NotFoundException("The requested customer does not exist!");
        }
        //Trả về bản ghi được tìm thấy
        return customer.get();
    }

    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO.getName(), customerDTO.getPhone(), customerDTO.getAddress());
        return customerRepository.save(customer);
    }

    public String deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            return "Customer ID does not exist, so not implement!";
        }
        customerRepository.deleteById(id);
        return "Deleted successfully!";
    }

    public Customer updateCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO.getName(), customerDTO.getPhone(), customerDTO.getAddress());
        return customerRepository.save(customer);
    }
}
