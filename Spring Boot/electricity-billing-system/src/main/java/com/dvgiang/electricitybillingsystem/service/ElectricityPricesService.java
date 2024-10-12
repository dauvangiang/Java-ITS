package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityPricesRequestDTO;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import com.dvgiang.electricitybillingsystem.repository.ElectricityPricesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElectricityPricesService {
    private final ElectricityPricesRepository electricityPricesRepository;

    public List<ElectricityPrices> getAllElectricityPrices() {
        return electricityPricesRepository.findAll();
    }

    public ElectricityPrices getElectricityPriceById(Long id) {
        Optional<ElectricityPrices> electricityPrices = electricityPricesRepository.findById(id);
        //Ném ra ngoại lệ NotFoundException nếu không tồn tại electricityPrices
        if (electricityPrices.isEmpty()) {
            throw new NotFoundException("Electricity prices with id = " + id + " does not exist!");
        }

        return electricityPrices.get();
    }

    public ElectricityPrices createElectricityPrices(ElectricityPricesRequestDTO electricityPricesRequestDTO) {
        ElectricityPrices electricityPrices = ElectricityPrices.builder()
            .name(electricityPricesRequestDTO.getName())
            .minUse(electricityPricesRequestDTO.getMinUse())
            .maxUse(electricityPricesRequestDTO.getMaxUse())
            .price(electricityPricesRequestDTO.getPrice())
            .build();

        return electricityPricesRepository.save(electricityPrices);
    }

    public ElectricityPrices updateElectricityPrices(ElectricityPricesRequestDTO electricityPricesRequestDTO) {
        Optional<ElectricityPrices> electricityPrices = electricityPricesRepository.findById(electricityPricesRequestDTO.getId());
        if(electricityPrices.isEmpty()) {
            throw new NotFoundException("Electricity prices not found!");
        }

        electricityPrices.get().setName(electricityPricesRequestDTO.getName());
        electricityPrices.get().setMinUse(electricityPricesRequestDTO.getMinUse());
        electricityPrices.get().setMaxUse(electricityPricesRequestDTO.getMaxUse());
        electricityPrices.get().setPrice(electricityPricesRequestDTO.getPrice());

        return electricityPricesRepository.save(electricityPrices.get());
    }

    public String deleteElectricityPricesById(Long id) {
        Optional<ElectricityPrices> configuration = electricityPricesRepository.findById(id);
        if (configuration.isEmpty()) {
            return "Configuration ID does not exist, so not implement!";
        }
        electricityPricesRepository.deleteById(id);
        return "Deleted successfully!";
    }
}
