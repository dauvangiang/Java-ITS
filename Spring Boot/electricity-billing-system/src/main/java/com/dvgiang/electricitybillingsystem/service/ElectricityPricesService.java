package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityPricesRequestDTO;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import com.dvgiang.electricitybillingsystem.repository.ElectricityPricesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElectricityPricesService {
    private final ElectricityPricesRepository electricityPricesRepository;

    public List<ElectricityPrices> getAllElectricityPrices() {
        return electricityPricesRepository.findAll();
    }

    public List<ElectricityPrices> getAllWithStatusOrderByASC() {
        return electricityPricesRepository.findAllWithStatusOrderByASC();
    }

    public ElectricityPrices getElectricityPriceById(Long id) {
        return electricityPricesRepository.findById(id)
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

        return electricityPricesRepository.save(electricityPrices);
    }

    public ElectricityPrices updateElectricityPrices(ElectricityPricesRequestDTO electricityPricesRequestDTO) {
        ElectricityPrices electricityPrices = electricityPricesRepository.findById(electricityPricesRequestDTO.getId())
                .orElseThrow(() -> new NotFoundException("Electricity prices does not exist!"));

        electricityPrices.updateFromDTO(electricityPricesRequestDTO);
        electricityPrices.setUpdateAt(new Date());

        log.info("Updated electricity prices (id = {})", electricityPricesRequestDTO.getId());

        return electricityPricesRepository.save(electricityPrices);
    }

    public String deleteElectricityPricesById(Long id) {
        if (electricityPricesRepository.existsById(id)) {
            electricityPricesRepository.deletePricesById(id);

            //logging
            log.info("Deleted electricity prices (id = {})", id);

            return "Deleted successfully!";
        } else {
            throw new NotFoundException("Electricity prices does not exist!");
        }
    }
}
