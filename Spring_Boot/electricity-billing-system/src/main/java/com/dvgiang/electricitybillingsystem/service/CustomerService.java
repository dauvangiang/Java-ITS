package com.dvgiang.electricitybillingsystem.service;

//import com.dvgiang.electricitybillingsystem.dto.query.CustomerWithUnpaidBillsDTO;
//import com.dvgiang.electricitybillingsystem.dto.request.CustomerDTO;
//import com.dvgiang.electricitybillingsystem.exception.ConflictException;
//import com.dvgiang.electricitybillingsystem.exception.ForbiddenException;
//import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
//import com.dvgiang.electricitybillingsystem.entity.Customer;
//import com.dvgiang.electricitybillingsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PermissionService permissionService;

    public List<Customer> getAllCustomers() {
//        if (!permissionService.hasPermission("READ_CUSTOMER")) {
//            throw new ForbiddenException("You do not have permission to view customers!");
//        }
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer does not exist!"));
    }

    public Customer getActiveCustomerById(Long id) {
        return customerRepository.findActiveCustomerById(id)
                .orElseThrow(() -> new NotFoundException("Customer does not exist!"));
    }

    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = Customer
            .builder()
            .fullName(customerDTO.getName())
            .phone(customerDTO.getPhone())
            .address(customerDTO.getAddress())
            .createdAt(new Date(System.currentTimeMillis()))
            .build();

        log.info("Created new customer");

        return customerRepository.save(customer);
    }

    public String deleteCustomerByID(Long id) {
        CustomerWithUnpaidBillsDTO customer = customerRepository.findCustomerWithUnpaidBillsById(id)
                .orElseThrow(() -> new NotFoundException("Customer does not exist!"));

        if (customer.getUnpaidBillsCount() > 0) {
            throw new ConflictException("Customer has " + customer.getUnpaidBillsCount() + " unpaid bills!");
        }

        log.info("Deleted a customer");

        //Cập nhật trạng thái KH thành 0 (ngừng sử dụng dịch vụ)
        customerRepository.deleteCustomerById(id);
        return "Deleted successfully!";
    }

    public Customer updateCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(customerDTO.getId())
                .orElseThrow(() -> new NotFoundException("Customer ID does not exist!"));

        customer.updateFromDTO(customerDTO);
        customer.setUpdatedAt(new Date());

        log.info("Updated a customer");

        return customerRepository.save(customer);
    }
}
