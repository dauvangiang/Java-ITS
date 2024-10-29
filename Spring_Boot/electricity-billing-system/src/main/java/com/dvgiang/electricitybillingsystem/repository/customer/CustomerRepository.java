package com.dvgiang.electricitybillingsystem.repository.customer;

import com.dvgiang.electricitybillingsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom {
}
