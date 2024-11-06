package com.dvgiang.electricitybillingsystem.scheduler.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class JobListenerCustom extends JobListenerSupport {
    @Override
    public String getName() {
        return "Joblistener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("Công việc sắp được thực hiện...");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobExecutionException) {
        System.out.println("Công việc đã được thực hiện xong!");
    }
}


