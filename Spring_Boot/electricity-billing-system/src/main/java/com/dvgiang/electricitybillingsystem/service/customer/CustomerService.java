package com.dvgiang.electricitybillingsystem.service.customer;

import com.dvgiang.electricitybillingsystem.dto.request.CustomerDTO;
import com.dvgiang.electricitybillingsystem.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    List<Customer> getCustomers();
    Customer getCustomerById(Long id, boolean requiredLogin);
    Customer createCustomer(CustomerDTO customerDTO);
    String deleteCustomerByID(Long id);
    Customer updateCustomer(CustomerDTO customerDTO);
    List<Customer> getCustomersByPage(int page, long limit);
}


