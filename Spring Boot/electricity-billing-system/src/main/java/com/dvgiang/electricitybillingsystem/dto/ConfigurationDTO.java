package com.dvgiang.electricitybillingsystem.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ConfigurationDTO {
    @NotEmpty(message = "Configuration's name is required!")
    private String name;
    @NotNull(message = "minUse is required!")
    private Integer minUse;
    @NotNull(message = "maxUse is required!")
    private Integer maxUse;
    @NotNull(message = "price is required!")
    private Float price;

    public String getName() {
        return name;
    }

    public Integer getMinUse() {
        return minUse;
    }

    public Integer getMaxUse() {
        return maxUse;
    }

    public Float getPrice() {
        return price;
    }
}
