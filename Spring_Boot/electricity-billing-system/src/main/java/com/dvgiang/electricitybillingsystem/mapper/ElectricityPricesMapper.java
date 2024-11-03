package com.dvgiang.electricitybillingsystem.mapper;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityPricesRequestDTO;
import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ElectricityPricesMapper {
    ElectricityPrices toPrice(ElectricityPricesRequestDTO dto);
    void updatePrice(@MappingTarget ElectricityPrices price, ElectricityPricesRequestDTO dto);
}
