package com.demo.backend.service.impl;

import com.demo.backend.demo.ScheduleSetting;
import com.demo.backend.mapper.ScheduleSettingMapper;
import com.demo.backend.service.ScheduleTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private ScheduleSettingMapper qurtaztDao;

    @Override
    public void startQurtaz(Integer id) {
    }

    @Override
    public void updateQurtaz(ScheduleSetting scheduleSetting) {

    }

    @Override
    public void stopQurtaz(Integer id) {

    }

    @Override
    public void restartQurtaz(Integer id) {

    }

    @Override
    public void removeQurtaz(Integer id) {

    }
}
