package com.dvgiang.electricitybillingsystem.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectricityPricesRequestDTO {
    @NotNull(message = "id is required!")
    private Long id;

    @NotEmpty(message = "name is required!")
    private String name;

    @NotNull(message = "minUse is required!")
    private Integer minUse;

    @NotNull(message = "maxUse is required!")
    private Integer maxUse;

    @NotNull(message = "price is required!")
    private Float price;

    private Integer status = 1;

    private Date updatedAt = new Date();
}
