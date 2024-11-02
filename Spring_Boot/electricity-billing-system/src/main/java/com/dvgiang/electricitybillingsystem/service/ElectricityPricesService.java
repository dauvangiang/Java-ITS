package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityPricesRequestDTO;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
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

    public List<ElectricityPrices> getAllElectricityPrices(boolean isOrderByPrices, String type) {
        return electricityPricesRepo.getAllElectricityPrices(isOrderByPrices, type);
    }

    public ElectricityPrices getElectricityPriceById(Long id) {
        return electricityPricesRepo.getElectricityPricesById(id)
                .orElseThrow(() -> new NotFoundException("Electricity prices does not exist!"));
    }

    public ElectricityPrices createElectricityPrices(ElectricityPricesRequestDTO electricityPricesRequestDTO) {
        ElectricityPrices electricityPrices = ElectricityPrices.builder()
            .name(electricityPricesRequestDTO.getName())
            .minUse(electricityPricesRequestDTO.getMinUse())
            .maxUse(electricityPricesRequestDTO.getMaxUse())
            .price(electricityPricesRequestDTO.getPrice())
            .createdAt(new Date())
            .build();

        log.info("Created new electricity prices (name: {})", electricityPricesRequestDTO.getName());

        return electricityPricesRepo.save(electricityPrices);
    }

    public ElectricityPrices updateElectricityPrices(ElectricityPricesRequestDTO electricityPricesRequestDTO) {
        ElectricityPrices electricityPrices = electricityPricesRepo.getElectricityPricesById(electricityPricesRequestDTO.getId())
                .orElseThrow(() -> new NotFoundException("Electricity prices does not exist!"));

        electricityPrices.updateFromDTO(electricityPricesRequestDTO);
        electricityPrices.setUpdateAt(new Date());

        log.info("Updated electricity prices (id = {})", electricityPricesRequestDTO.getId());

        return electricityPricesRepo.save(electricityPrices);
    }

    public String deleteElectricityPricesById(Long id) {
        if (electricityPricesRepo.existsPriceById(id)) {
            electricityPricesRepo.deleteElectricityPricesById(id);

            //logging
            log.info("Deleted electricity prices (id = {})", id);

            return "Deleted successfully!";
        } else {
            throw new NotFoundException("Electricity prices does not exist!");
        }
    }
}
