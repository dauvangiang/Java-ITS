package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.entity.Customer;
import com.dvgiang.electricitybillingsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
  private final CustomerRepository customerRepository;

  //Chạy lúc 9h00 ngày m1 hàng tháng
  @Async
  @Scheduled(cron = "0 0 9 1 * *")
//  @Scheduled(fixedRate = 5000)
  public void sendElectricityBillPaymentNotice() {
    List<Customer> customers = customerRepository.findAll();
    for (Customer c : customers) {
      //Giả sử: quá trình gửi thông báo qua sms
      System.out.printf(
          "Đã gửi thông báo đóng tiền điện đến khách hàng %s, SĐT: %s\n",
          c.getFullName(),
          c.getPhone()
      );
    }
    System.out.println("Hoàn tất gửi thông báo!");
  }
}
