package com.dvgiang.electricitybillingsystem.mapper.electricitybill;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityBillDTO;
import com.dvgiang.electricitybillingsystem.entity.ElectricityBill;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ElectricityBillMapper {
    public ElectricityBill toBill(ElectricityBillDTO dto) {
        if (dto == null) {
            return null;
        }

        //Số điện đã dùng
        int used = dto.getCurrentReading() - dto.getPreviousReading();

        return ElectricityBill.builder()
                .customerId(dto.getCustomerId())
                .writingDate(new Date())
                .billingPeriod(dto.getBillingPeriod())
                .previousReading(dto.getPreviousReading())
                .currentReading(dto.getCurrentReading())
                .used(used)
                .paymentStatus(0)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }
}
