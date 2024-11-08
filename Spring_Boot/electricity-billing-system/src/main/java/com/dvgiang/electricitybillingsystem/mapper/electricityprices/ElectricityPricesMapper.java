package com.dvgiang.electricitybillingsystem.mapper.electricityprices;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityPricesDTO;
import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ElectricityPricesMapper {
    public ElectricityPrices toPrice(ElectricityPricesDTO dto) {
        if (dto == null) {
            return null;
        }

        return ElectricityPrices.builder()
                .name(dto.getName())
                .minUse(dto.getMinUse())
                .maxUse(dto.getMaxUse())
                .price(dto.getPrice())
                .status(1)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    public void updatePrice(ElectricityPrices price, ElectricityPricesDTO dto) {
        if (dto == null) {
            return;
        }

        price.setName(dto.getName());
        price.setMaxUse(dto.getMaxUse());
        price.setMinUse(dto.getMinUse());
        price.setPrice(dto.getPrice());
        price.setUpdatedAt(new Date());
    }
}