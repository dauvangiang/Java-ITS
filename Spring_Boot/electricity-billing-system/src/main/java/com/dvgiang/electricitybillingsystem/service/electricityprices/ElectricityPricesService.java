package com.dvgiang.electricitybillingsystem.service.electricityprices;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityPricesDTO;
import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ElectricityPricesService {
    List<ElectricityPrices> getAllElectricityPrices(boolean isOrderByPrices, String type);
    ElectricityPrices getElectricityPriceById(Long id);
    ElectricityPrices createElectricityPrices(ElectricityPricesDTO dto);
    ElectricityPrices updateElectricityPrices(ElectricityPricesDTO dto);
    String deleteElectricityPricesById(Long id);
}
