package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityPricesRequestDTO;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.model.Configuration;
import com.dvgiang.electricitybillingsystem.repository.ConfigurationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;

    //Constructor DI
    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public List<Configuration> getAllConfigurations() {
        return configurationRepository.findAll();
    }

    public Configuration getConfigById(Long id) {
        Optional<Configuration> configuration = configurationRepository.findById(id);
        //Ném ra ngoại lệ NotFoundException nếu không tồn tại configuration có id tương ứng
        if (configuration.isEmpty()) {
            throw new NotFoundException("Configuration with id = " + id + " does not exist!");
        }
        //Trả về configuration có id được yêu cầu
        return configuration.get();
    }

    public Configuration createConfig(ElectricityPricesRequestDTO electricityPricesRequestDTO) {
        Configuration configuration = new Configuration(electricityPricesRequestDTO.getName(), electricityPricesRequestDTO.getMinUse(), electricityPricesRequestDTO.getMaxUse(), electricityPricesRequestDTO.getPrice());
        return configurationRepository.save(configuration);
    }

    public Configuration updateConfig(ElectricityPricesRequestDTO electricityPricesRequestDTO) {
        Configuration configuration = new Configuration(electricityPricesRequestDTO.getName(), electricityPricesRequestDTO.getMinUse(), electricityPricesRequestDTO.getMaxUse(), electricityPricesRequestDTO.getPrice());
        return configurationRepository.save(configuration);
    }

    public String deleteConfigById(Long id) {
        Optional<Configuration> configuration = configurationRepository.findById(id);
        if (configuration.isEmpty()) {
            return "Configuration ID does not exist, so not implement!";
        }
        configurationRepository.deleteById(id);
        return "Deleted successfully!";
    }
}
