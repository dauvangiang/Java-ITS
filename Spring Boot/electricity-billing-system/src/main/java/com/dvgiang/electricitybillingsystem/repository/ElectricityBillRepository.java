package com.dvgiang.electricitybillingsystem.repository;

import com.dvgiang.electricitybillingsystem.entity.ElectricityBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricityBillRepository extends JpaRepository<ElectricityBill, Long> {

  @Query("SELECT b FROM ElectricityBill b WHERE b.customer.id = :id AND b.paymentStatus = 0")
  List<ElectricityBill> findUnpaidBillsByCustomerId(@Param("id") Long id);
}
