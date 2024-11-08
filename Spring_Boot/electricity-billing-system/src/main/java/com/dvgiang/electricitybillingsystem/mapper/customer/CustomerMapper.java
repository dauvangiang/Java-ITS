package com.dvgiang.electricitybillingsystem.mapper.customer;

import com.dvgiang.electricitybillingsystem.dto.request.CustomerDTO;
import com.dvgiang.electricitybillingsystem.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }

        return Customer.builder()
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .status(1)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    public void updateCustomer(Customer customer, CustomerDTO dto) {
        if (dto == null) {
            return;
        }

        customer.setFullName(dto.getFullName());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        customer.setUpdatedAt(new Date());
    }
}
