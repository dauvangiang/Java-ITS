package com.dvgiang.electricitybillingsystem.repository;

import com.dvgiang.electricitybillingsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//Đối tượng được quản lý: Customer
//Kiểu dữ liệu của khóa chính: Long
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
