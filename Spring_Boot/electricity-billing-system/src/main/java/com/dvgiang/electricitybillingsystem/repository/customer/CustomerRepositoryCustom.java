package com.dvgiang.electricitybillingsystem.repository.customer;

import com.dvgiang.electricitybillingsystem.dto.query.UnpaidBillCountsDTO;
import com.dvgiang.electricitybillingsystem.entity.Customer;

import java.util.List;
import java.util.Optional;

//Khai bao cac ham giao tiep voi db
public interface CustomerRepositoryCustom {
    Optional<Customer> getCustomerById(Long id);
    List<Customer> getCustomers();
    Optional<UnpaidBillCountsDTO> getUnpaidBillCountsByCustomerId(Long id);
    void deleteCustomerById(Long id);
    List<Customer> getCustomersByPage(int page, long limit);
}
