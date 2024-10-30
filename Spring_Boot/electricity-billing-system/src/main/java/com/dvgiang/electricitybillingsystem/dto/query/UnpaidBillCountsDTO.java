package com.dvgiang.electricitybillingsystem.dto.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnpaidBillCountsDTO {
    private Long customerId;
    private Long unpaidBillsCount;
}
