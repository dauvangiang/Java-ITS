package com.dvgiang.electricitybillingsystem.repository.electricityprices;

import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricityPricesRepo extends JpaRepository<ElectricityPrices, Long>, ElectricityPricesRepoCustom {
}
