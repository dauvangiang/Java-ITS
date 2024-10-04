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
    public ResponseEntity<Object> getAllConfigurations() {
        List<Configuration> listConfiguration = configurationService.getAllConfigurations();
        return ResponseHandler.responseBuilder(listConfiguration, HttpStatus.OK, null, null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getConfigById(@PathVariable Long id) {
        Configuration configuration = configurationService.getConfigById(id);
        return ResponseHandler.responseBuilder(configuration, HttpStatus.OK, null, null);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createConfig(@Valid @RequestBody ConfigurationDTO configurationDTO) {
        Configuration configuration = configurationService.createConfig(configurationDTO);
        return ResponseHandler.responseBuilder(configuration, HttpStatus.OK, null, null);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateConfig(@Valid @RequestBody ConfigurationDTO configurationDTO) {
        Configuration configuration = configurationService.updateConfig(configurationDTO);
        return ResponseHandler.responseBuilder(configuration, HttpStatus.OK, null, null);
    }

    @GetMapping("/delete/{id}")
    public String deleteConfigById(@PathVariable Long id) {
        return configurationService.deleteConfigById(id);
    }
}
