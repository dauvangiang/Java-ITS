package com.dvgiang.electricitybillingsystem.repository.electricitybill;

import com.dvgiang.electricitybillingsystem.entity.ElectricityBill;

import java.util.List;

public interface ElectricityBillRepoCustom {
    List<ElectricityBill> getUnpaidBillsByCustomerId(Long id);
}
