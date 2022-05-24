package com.demo.backend.service;

import com.demo.backend.demo.ScheduleSetting;

public interface ScheduleTestService {
    public void test1();

    public void test2();

    public void startQurtaz(Integer id);

    public void updateQurtaz(ScheduleSetting scheduleSetting);

    public void stopQurtaz(Integer id);

    public void restartQurtaz(Integer id);

    public void removeQurtaz(Integer id);
}
