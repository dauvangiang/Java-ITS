package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.query.UnpaidBillCountsDTO;
import com.dvgiang.electricitybillingsystem.dto.request.CustomerDTO;
import com.dvgiang.electricitybillingsystem.entity.Customer;
import com.dvgiang.electricitybillingsystem.exception.ConflictException;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.mapper.customer.CustomerMapper;
import com.dvgiang.electricitybillingsystem.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final PermissionService permissionService;
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public List<Customer> getAllCustomers() {
//        if (!permissionService.hasPermission("READ_CUSTOMER")) {
//            throw new ForbiddenException("You do not have permission to view customers!");
//        }

        return customerRepository.getCustomers();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.getCustomerById(id)
                .orElseThrow(() -> new NotFoundException("Customer does not exist!"));
    }

    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = mapper.toCustomer(customerDTO);

        log.info("Created new customer");

        return customerRepository.save(customer);
    }

    public String deleteCustomerByID(Long id) {
        UnpaidBillCountsDTO billCounts = customerRepository.getUnpaidBillCountsByCustomerId(id)
                .orElseThrow(() -> new NotFoundException("Customer does not exist!"));

        if (billCounts != null && billCounts.getUnpaidBillsCount() > 0) {
            throw new ConflictException("Customer has " + billCounts.getUnpaidBillsCount() + " unpaid bills!");
        }

        log.info("Deleted a customer");

        customerRepository.deleteCustomerById(id);
        return "Deleted successfully!";
    }

    public Customer updateCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(customerDTO.getId())
                .orElseThrow(() -> new NotFoundException("Customer ID does not exist!"));

        mapper.updateCustomer(customer, customerDTO);

        log.info("Updated a customer");

        return customerRepository.save(customer);
    }

    public List<Customer> getCustomersByPage(int page, long limit) {
        return customerRepository.getCustomersByPage(page, limit);
    }
}
