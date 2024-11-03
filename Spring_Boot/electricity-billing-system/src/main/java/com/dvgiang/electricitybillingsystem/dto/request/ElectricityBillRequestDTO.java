package com.dvgiang.electricitybillingsystem.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElectricityBillRequestDTO {
    @NotNull(message = "Customer ID is required")
    private Long customerId;
    @NotEmpty(message = "Billing period is required")
    private String billingPeriod;
    @NotNull(message = "Customer ID is required")
    private Integer previousReading;
    @NotNull(message = "Customer ID is required")
    private Integer currentReading;
}
