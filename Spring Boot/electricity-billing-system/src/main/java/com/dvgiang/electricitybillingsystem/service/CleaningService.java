package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.repository.TokenRevokedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor //tạo ra constructor cho các thuộc tính final hoặc thuộc tính @NonNull
public class CleaningService {
  private final TokenRevokedRepository tokenRevokedRepository;

  @Async
//  @Scheduled(fixedDelay = 7*24*60*60*1000)
  @Scheduled(cron = "0 0 9 */3 * *")
  public void cleanUpRevokedTokensEvery7Days() {
    log.info("Clean up revoked tokens");
    Date threeHoursAgo = new Date(System.currentTimeMillis() - 3*60*60*1000);
    tokenRevokedRepository.deleteAllOlderThan(threeHoursAgo);
  }
}
