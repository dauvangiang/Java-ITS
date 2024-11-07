package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityPricesRequestDTO;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import com.dvgiang.electricitybillingsystem.mapper.ElectricityPricesMapper;
import com.dvgiang.electricitybillingsystem.repository.electricityprices.ElectricityPricesRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElectricityPricesService {
    private final ElectricityPricesRepo electricityPricesRepo;
    private final ElectricityPricesMapper mapper;

    public List<ElectricityPrices> getAllElectricityPrices(boolean isOrderByPrices, String type) {
        return electricityPricesRepo.getAllElectricityPrices(isOrderByPrices, type);
    }

    public ElectricityPrices getElectricityPriceById(Long id) {
        return electricityPricesRepo.getElectricityPricesById(id)
                .orElseThrow(() -> new NotFoundException("Electricity prices does not exist!"));
    }

    public ElectricityPrices createElectricityPrices(ElectricityPricesRequestDTO priceDTO) {
        ElectricityPrices price = mapper.toPrice(priceDTO);
        price.setCreatedAt(new Date());

        log.info("Created new electricity prices (name: {})", price.getName());

        return electricityPricesRepo.save(price);
    }

    public ElectricityPrices updateElectricityPrices(ElectricityPricesRequestDTO priceDTO) {
        ElectricityPrices price = electricityPricesRepo.getElectricityPricesById(priceDTO.getId())
                .orElseThrow(() -> new NotFoundException("Electricity prices does not exist!"));

        mapper.updatePrice(price, priceDTO);

        log.info("Updated electricity prices (id = {})", priceDTO.getId());

        return electricityPricesRepo.save(price);
    }

    public String deleteElectricityPricesById(Long id) {
        if (electricityPricesRepo.existsPriceById(id)) {
            electricityPricesRepo.deleteElectricityPricesById(id);

            log.info("Deleted electricity prices (id = {})", id);

            return "Deleted successfully!";
        } else {
            throw new NotFoundException("Electricity prices does not exist!");
        }
    }
}
