package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.model.Configuration;
import com.dvgiang.electricitybillingsystem.service.ConfigurationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class ConfigurationController {
    private final ConfigurationService configurationService;

    //Constructor DI
    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    //
    @GetMapping
    public List<Configuration> getAllConfigurations() {
        return configurationService.getAllConfigurations();
    }

    @GetMapping("/{id}")
    public Configuration getConfigById(@PathVariable Long id) {
        return configurationService.getConfigById(id);
    }

    @PostMapping("/create")
    public Configuration createConfig(@RequestBody Configuration configuration) {
        return configurationService.createConfig(configuration);
    }

    @PostMapping("/update")
    public Configuration updateConfig(@RequestBody Configuration configuration) {
        return configurationService.updateConfig(configuration);
    }

    @GetMapping("/delete/{id}")
    public String deleteConfigById(@PathVariable Long id) {
        return configurationService.deleteConfigById(id);
    }
}
