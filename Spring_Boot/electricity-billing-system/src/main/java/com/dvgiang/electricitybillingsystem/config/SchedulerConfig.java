package com.dvgiang.electricitybillingsystem.config;

import com.dvgiang.electricitybillingsystem.scheduler.job.NotificationJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {
    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(NotificationJob.class)
                .withIdentity("elecBillNotificationJob", "notification")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .withIdentity("elecBillNotificationTrigger", "notification")
                .forJob(jobDetail)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(10)
                                .repeatForever()
//                                .withRepeatCount(1)
                )
//                .withSchedule(
//                        CronScheduleBuilder.cronSchedule("0 0 8 1 * ?")
//                                .withMisfireHandlingInstructionFireAndProceed()
//                )
                .build();
    }
}
