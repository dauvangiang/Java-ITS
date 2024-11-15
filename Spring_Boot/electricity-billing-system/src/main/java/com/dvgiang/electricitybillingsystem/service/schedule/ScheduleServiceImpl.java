package com.dvgiang.electricitybillingsystem.service.schedule;

import com.dvgiang.electricitybillingsystem.entity.Customer;
import com.dvgiang.electricitybillingsystem.repository.customer.CustomerRepository;
import com.dvgiang.electricitybillingsystem.repository.token.RevokedTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final RevokedTokenRepository tokenRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Async
    @Scheduled(cron = "0 0 9 1 * *")
    public void sendElectricityBillPaymentNotice() {
        List<Customer> customers = customerRepository.getCustomers();
        for (Customer c : customers) {
            System.out.printf(
                    "Thông báo đóng tiền điện đến khách hàng %s, SĐT: %s\n",
                    c.getFullName(),
                    c.getPhone()
            );
        }
        System.out.println("Hoàn tất gửi thông báo!");
    }

    @Override
    @Async
    @Scheduled(cron = "0 0 9 */3 * *")
    public void cleanUpTokens() {
        log.info("Dọn dẹp token bị thu hồi!");
        Date threeHoursAgo = new Date(System.currentTimeMillis() - 3*60*60*1000);
        tokenRepository.deleteAllOlderThan(threeHoursAgo);
    }
}
