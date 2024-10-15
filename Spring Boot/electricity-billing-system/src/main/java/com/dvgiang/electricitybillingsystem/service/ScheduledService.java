package com.dvgiang.electricitybillingsystem.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
  //Chạy mỗi 10s
  @Scheduled(fixedRate = 10000)
  public void runTaskEveryTenSeconds() {
    //code
  }

  //Chạy sau khi tác vụ trước kết thúc 30s
  @Scheduled(fixedDelay = 30000)
  public void runTaskWithDelay() {
    //code
  }

  //Chạy mỗi 12h00p00 hàng ngày
  @Scheduled(cron = "0 0 12 * * *")
  public void runTaskEveryDay() {
    //code
  }
}
