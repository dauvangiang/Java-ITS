package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.request.CustomerDTO;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.entity.Customer;
import com.dvgiang.electricitybillingsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

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
        log.info("Add new customer");
        Customer customer = Customer
            .builder()
            .fullName(customerDTO.getName())
            .phone(customerDTO.getPhone())
            .address(customerDTO.getAddress())
            .build();
        return customerRepository.save(customer);
    }

    public String deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new NotFoundException("Customer not exist!");
        }
        log.info("Delete a customer");
        customerRepository.deleteById(id);
        return "Deleted successfully!";
    }

    public Customer updateCustomer(CustomerDTO customerDTO) {
        Optional<Customer> customer = customerRepository.findById(customerDTO.getId());
        if (customer.isEmpty()) {
            throw new NotFoundException("Customer ID not found!");
        }

        Customer customerUpdate = customer.get();

        customerUpdate.setFullName(customerDTO.getName());
        customerUpdate.setPhone(customerDTO.getPhone());
        customerUpdate.setAddress(customerDTO.getAddress());

        return customerRepository.save(customerUpdate);
    }
}
