package com.dvgiang.electricitybillingsystem.mapper;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityBillRequestDTO;
import com.dvgiang.electricitybillingsystem.entity.ElectricityBill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ElectricityBillMapper {
    @Mapping(target = "used", expression = "java(dto.getCurrentReading() - dto.getPreviousReading())")
    ElectricityBill toBill(ElectricityBillRequestDTO dto);
}
