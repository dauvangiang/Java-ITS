package com.dvgiang.electricitybillingsystem.service.electricitybill;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityBillDTO;
import com.dvgiang.electricitybillingsystem.entity.ElectricityBill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ElectricityBillService {
    ElectricityBill writeElectricityBilling(ElectricityBillDTO dto);
    List<ElectricityBill> getUnpaidBillsByCustomerId(Long id);
}
