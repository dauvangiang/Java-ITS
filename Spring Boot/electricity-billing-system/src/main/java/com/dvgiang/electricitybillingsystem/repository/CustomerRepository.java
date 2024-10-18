package com.dvgiang.electricitybillingsystem.repository;

import com.dvgiang.electricitybillingsystem.dto.query.CustomerWithUnpaidBillsDTO;
import com.dvgiang.electricitybillingsystem.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//Đối tượng được quản lý: Customer
//Kiểu dữ liệu của khóa chính: Long
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.status = 0 WHERE c.id = :id")
    void deleteCustomerById(@Param("id") Long id);

    //Constructor expression: com.dvgiang.electricitybillingsystem.dto.query.CustomerWithUnpaidBillsDTO(...)
    @Query("SELECT new com.dvgiang.electricitybillingsystem.dto.query.CustomerWithUnpaidBillsDTO(c.id, COUNT(b)) " +
            "FROM Customer c LEFT JOIN ElectricityBill b ON c.id = b.customer.id AND b.paymentStatus = 0" +
            "WHERE c.id = :customerId GROUP BY c.id")
    Optional<CustomerWithUnpaidBillsDTO> findCustomerWithUnpaidBillsById(@Param("customerId") Long customerId);

    @Query("SELECT c FROM Customer c WHERE c.id = :id AND c.status = 1")
    Optional<Customer> findActiveCustomerById(@Param("id") Long id);
}
