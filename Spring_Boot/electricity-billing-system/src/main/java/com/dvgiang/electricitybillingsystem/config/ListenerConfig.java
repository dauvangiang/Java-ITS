package com.dvgiang.electricitybillingsystem.config;

import com.dvgiang.electricitybillingsystem.scheduler.listener.JobListenerCustom;
import com.dvgiang.electricitybillingsystem.scheduler.listener.TriggerListenerCustom;
import lombok.RequiredArgsConstructor;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.KeyMatcher;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor
public class ListenerConfig {
    private final Scheduler scheduler;
    private final JobListenerCustom jobListener;
    private final TriggerListenerCustom triggerListener;
    @EventListener(ContextRefreshedEvent.class)
    public void regiterListener() throws SchedulerException {
        scheduler.getListenerManager().addJobListener(
                jobListener,
                KeyMatcher.keyEquals(new JobKey("elecBillNotificationJob", "notification"))
        );
        scheduler.getListenerManager().addTriggerListener(triggerListener);
    }
}
