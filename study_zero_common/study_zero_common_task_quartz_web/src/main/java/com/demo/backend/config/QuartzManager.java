package com.demo.backend.config;

import com.demo.backend.demo.ScheduleSetting;
import lombok.Data;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Data
public class QuartzManager {

    public static QuartzManager quartzManager;

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void init(){
        quartzManager = this;
        quartzManager.scheduler = this.scheduler;

    }
    /**
     * 添加任务*/
    @SuppressWarnings("unchecked")
    public static void addJob(ScheduleSetting task) {
        try {
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(task.getClassName()).newInstance()
                    .getClass());
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(task.getJobName(), task.getClassName())// 任务名称和组构成任务key
                    .build();
            // 定义调度触发规则
            // 使用cornTrigger规则
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getJobName(), task.getClassName())// 触发器key
                    .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(task.getCron())).startNow().build();
            // 把作业和触发器注册到任务调度中
            quartzManager.scheduler.scheduleJob(jobDetail, (org.quartz.Trigger) trigger);
            // 启动
            if (! quartzManager.scheduler.isShutdown()) {
                quartzManager.scheduler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
