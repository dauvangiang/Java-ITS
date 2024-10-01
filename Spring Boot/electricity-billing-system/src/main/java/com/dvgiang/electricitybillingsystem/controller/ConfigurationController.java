package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.ConfigurationDTO;
import com.dvgiang.electricitybillingsystem.model.Configuration;
import com.dvgiang.electricitybillingsystem.response.ResponseHandler;
import com.dvgiang.electricitybillingsystem.service.ConfigurationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getConfigById(@PathVariable Long id) {
        return ResponseHandler.responseBuilder(configurationService.getConfigById(id), HttpStatus.OK, null, null);
    }

    @PostMapping("/create")
    public Configuration createConfig(@Valid @RequestBody ConfigurationDTO configurationDTO) {
        return configurationService.createConfig(configurationDTO);
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
