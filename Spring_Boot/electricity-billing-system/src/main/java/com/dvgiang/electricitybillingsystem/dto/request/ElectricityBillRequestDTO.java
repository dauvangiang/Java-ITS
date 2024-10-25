package com.dvgiang.electricitybillingsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElectricityBillRequestDTO {
  private Long customerId;
  private String billingPeriod;
  private Integer previousReading;
  private Integer currentReading;
}
