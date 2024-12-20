package com.dvgiang.electricitybillingsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectricityPricesDTO {
    @NotNull(message = "Mã số không được để trống")
    private Long id;

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotNull(message = "Mức sử dụng điện tối thiếu không được để trống")
    private Integer minUse;

    @NotNull(message = "Mức sử dụng điện tối đa không được để trống")
    private Integer maxUse;

    @NotNull(message = "Giá tiền không được để trống")
    private Float price;
}
