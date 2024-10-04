package com.dvgiang.electricitybillingsystem.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class HistoryDTO {
    @NotNull(message = "customerId is required!")
    private Long customerId;
    @NotEmpty(message = "billingPeriod is required!")
    private String billingPeriod;
    @NotNull(message = "used is required!")
    private Integer used;

    public Long getCustomerId() { return customerId; }

    public String getBillingPeriod() {
        return billingPeriod;
    }

    public Integer getUsed() {
        return used;
    }
}
