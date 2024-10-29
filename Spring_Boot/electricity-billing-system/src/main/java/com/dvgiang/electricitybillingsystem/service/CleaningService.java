package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.repository.token.RevokedTokenRepository;
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
  private final RevokedTokenRepository revokedTokenRepository;

  @Async
  @Scheduled(cron = "0 0 9 */3 * *")
  public void cleanUpRevokedTokens() {
    log.info("Clean up revoked tokens");
    Date threeHoursAgo = new Date(System.currentTimeMillis() - 3*60*60*1000);
    revokedTokenRepository.deleteAllOlderThan(threeHoursAgo);
  }
}
