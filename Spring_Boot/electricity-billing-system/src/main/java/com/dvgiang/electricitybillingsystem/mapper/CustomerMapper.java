package com.dvgiang.electricitybillingsystem.mapper;

import com.dvgiang.electricitybillingsystem.dto.request.CustomerDTO;
import com.dvgiang.electricitybillingsystem.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerDTO dto);
    CustomerDTO toCustomerDTO(Customer customer);
    void updateCustomer(@MappingTarget Customer customer, CustomerDTO dto);
}
