package com.dvgiang.electricitybillingsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElectricityBillRequestDTO {
    @NotNull(message = "Mã khách hàng không được để trống")
    private Long customerId;

    @NotBlank(message = "Chu kỳ thanh toán không được để trống")
    private String billingPeriod;

    @NotNull(message = "Số điện đầu không được để trống")
    private Integer previousReading;

    @NotNull(message = "Số điện cuối không được để trống")
    private Integer currentReading;

    private Date writingDate = new Date();

    private Integer paymentStatus = 0;

    private Date updatedAt = new Date();
}
