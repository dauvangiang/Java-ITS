package com.dvgiang.electricitybillingsystem.dto.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerWithUnpaidBillsDTO {
    private Long customerId;
    private Long unpaidBillsCount;
}
