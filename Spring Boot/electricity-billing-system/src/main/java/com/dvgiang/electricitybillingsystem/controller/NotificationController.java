package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visitor/notices")
@RequiredArgsConstructor
public class NotificationController {
//  private final NotificationService notificationService;
//
//  @GetMapping
//  public String sendNotice() {
//    notificationService.sendElectricityBillPaymentNotice();
//    return "Quá trình gửi thông báo đang diễn ra...";
//  }
}