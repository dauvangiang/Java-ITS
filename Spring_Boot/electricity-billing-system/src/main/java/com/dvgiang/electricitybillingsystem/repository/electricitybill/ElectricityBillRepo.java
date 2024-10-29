package com.dvgiang.electricitybillingsystem.repository.electricitybill;

import com.dvgiang.electricitybillingsystem.entity.ElectricityBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricityBillRepo extends JpaRepository<ElectricityBill, Long>, ElectricityBillRepoCustom {
}
