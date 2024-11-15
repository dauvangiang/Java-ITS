package com.dvgiang.electricitybillingsystem.service.electricitybill;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityBillDTO;
import com.dvgiang.electricitybillingsystem.entity.ElectricityBill;

import java.util.List;

public interface ElectricityBillService {
    ElectricityBill writeElectricityBilling(ElectricityBillDTO dto);
    List<ElectricityBill> getUnpaidBillsByCustomerId(Long id);
}
