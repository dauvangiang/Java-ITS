package com.dvgiang.electricitybillingsystem.dto.query;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnpaidBillCountsDTO {
    private Long customerId;
    private Long unpaidBillsCount;
}
