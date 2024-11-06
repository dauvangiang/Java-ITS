package com.dvgiang.electricitybillingsystem.scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class NotificationJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Thread.sleep(500);
            System.err.println("Thông báo đóng tiền điền!");
            Thread.sleep(500);
        } catch (Exception e) {
            //code
        }

    }
}
