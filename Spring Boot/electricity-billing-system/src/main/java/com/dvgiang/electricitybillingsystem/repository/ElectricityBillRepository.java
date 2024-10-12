package com.dvgiang.electricitybillingsystem.repository;

import com.dvgiang.electricitybillingsystem.entity.ElectricityBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricityBillRepository extends JpaRepository<ElectricityBill, Long> {

  List<ElectricityBill> findAllByCustomerId(Long customerId);
}
