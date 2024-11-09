package com.dvgiang.electricitybillingsystem.service.schedule;

import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    void sendElectricityBillPaymentNotice();
    void cleanUpTokens();
}
