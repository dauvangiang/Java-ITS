package com.dvgiang.electricitybillingsystem.dto;

//Đối tượng ánh xạ Request Body khi client thực hiện getHistoryDetail
public class HistoryRequest {
    private Long customerId;
    private String billingPeriod;
    private Integer used;

    public Long getCustomerId() {
        return customerId;
    }

    public String getBillingPeriod() {
        return billingPeriod;
    }

    public Integer getUsed() {
        return used;
    }
}
