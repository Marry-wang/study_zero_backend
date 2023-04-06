package com.demo.backend.service;

import com.demo.backend.demo.ScheduleSetting;
import org.quartz.SchedulerException;

public interface ScheduleTestService {
    public void test1();

    public void test2();

    public void initSchedule()  throws SchedulerException;

    public void startQurtaz(ScheduleSetting scheduleSetting) throws SchedulerException;

    public void updateQurtaz(ScheduleSetting scheduleSetting) throws SchedulerException;

    public void stopQurtaz(ScheduleSetting scheduleSetting) throws SchedulerException;

    public void restartQurtaz(ScheduleSetting scheduleSetting) throws SchedulerException;

    public void removeQurtaz(ScheduleSetting scheduleSetting) throws SchedulerException;
}
