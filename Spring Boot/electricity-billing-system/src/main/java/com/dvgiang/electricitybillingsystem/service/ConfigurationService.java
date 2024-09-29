package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.model.Configuration;
import com.dvgiang.electricitybillingsystem.repository.ConfigurationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;

    //Cóntructor DI
    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public List<Configuration> getAllConfigurations() {
        return configurationRepository.findAll();
    }

    public Configuration getConfigById(Long id) {
        return configurationRepository.findById(id).get();
    }

    public Configuration createConfig(Configuration configuration) {
        return configurationRepository.save(configuration);
    }

    public Configuration updateConfig(Configuration configuration) {
        return configurationRepository.save(configuration);
    }

    public String deleteConfigById(Long id) {
        configurationRepository.deleteById(id);
        return "Deleted successfully!";
    }
}
