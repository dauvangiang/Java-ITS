package com.dvgiang.electricitybillingsystem.repository.electricityprices;

import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;

import java.util.List;
import java.util.Optional;

public interface ElectricityPricesRepoCustom {
    List<ElectricityPrices> getAllElectricityPrices(boolean isOrderByPrices, String type);
    Optional<ElectricityPrices> getElectricityPricesById(Long id);
    boolean existsPriceById(Long id);
    void deleteElectricityPricesById(Long id);
}
