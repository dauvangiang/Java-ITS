package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.ConfigurationDTO;
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
        if (configuration.isEmpty()) {
            throw new NotFoundException("Configuration with id = " + id + " does not exist!");
        }
        return configuration.get();
    }

    public Configuration createConfig(ConfigurationDTO configurationDTO) {
        Configuration configuration = new Configuration(configurationDTO.getName(), configurationDTO.getMinUse(), configurationDTO.getMaxUse(), configurationDTO.getPrice());
        return configurationRepository.save(configuration);
    }

    public Configuration updateConfig(Configuration configuration) {
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
