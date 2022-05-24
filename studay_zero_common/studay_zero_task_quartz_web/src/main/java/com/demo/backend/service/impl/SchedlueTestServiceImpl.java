package com.demo.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.backend.config.QuartzManager;
import com.demo.backend.demo.ScheduleSetting;
import com.demo.backend.mapper.ScheduleSettingMapper;
import com.demo.backend.service.ScheduleTestService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedlueTestServiceImpl implements ScheduleTestService {

    @Override
    public void test1() {
        System.out.println("111111111111");
    }

    @Override
    public void test2() {
        System.out.println("2222222222222");
    }

    @Override
    public void initSchedule() throws SchedulerException {
        // 这里获取任务信息数据
        List<ScheduleSetting> jobList = qurtaztDao.selectList(new QueryWrapper<ScheduleSetting>());
        for (ScheduleSetting task : jobList) {
            if (0==task.getStatus()) {
                QuartzManager.addJob(task);
            }
        }
    }

    @Autowired
    private Scheduler scheduler;



    @Autowired
    private ScheduleSettingMapper qurtaztDao;

    @Override
    public void startQurtaz(ScheduleSetting scheduleSetting) throws SchedulerException {
        QuartzManager.addJob(scheduleSetting);
//        JobKey jobKey = JobKey.jobKey(scheduleSetting.getJobName(), scheduleSetting.getClassName());
//        scheduler.triggerJob(jobKey);
    }

    @Override
    public void updateQurtaz(ScheduleSetting scheduleSetting) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleSetting.getJobName(), scheduleSetting.getClassName());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleSetting.getCron());

        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        scheduler.rescheduleJob(triggerKey, trigger);

    }

    @Override
    public void stopQurtaz(ScheduleSetting scheduleSetting) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduleSetting.getJobName(), scheduleSetting.getClassName());
        scheduler.pauseJob(jobKey);

    }

    @Override
    public void restartQurtaz(ScheduleSetting scheduleSetting) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduleSetting.getJobName(), scheduleSetting.getClassName());
        scheduler.resumeJob(jobKey);

    }

    @Override
    public void removeQurtaz(ScheduleSetting scheduleSetting) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduleSetting.getJobName(), scheduleSetting.getClassName());
        scheduler.deleteJob(jobKey);
    }
}
