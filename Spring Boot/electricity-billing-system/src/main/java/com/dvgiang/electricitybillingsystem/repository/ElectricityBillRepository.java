package com.dvgiang.electricitybillingsystem.repository;

import com.dvgiang.electricitybillingsystem.model.ElectricityBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElectricityBillRepository extends JpaRepository<ElectricityBill, Long> {

  List<ElectricityBill> findAllByCustomerId(Long customerId);
}
