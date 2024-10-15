package com.dvgiang.electricitybillingsystem.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
  @Async
  public void executeAsyncTask() {
    //code
  }
}


