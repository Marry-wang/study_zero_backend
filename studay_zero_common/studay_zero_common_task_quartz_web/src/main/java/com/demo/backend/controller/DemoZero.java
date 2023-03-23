package com.demo.backend.controller;

import com.demo.backend.demo.ScheduleSetting;
import com.demo.backend.service.ScheduleTestService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DemoZero
 * @Author 王孟伟
 * @Date 2021/10/9 14:39
 * @Version 1.0
 */
@RestController
public class DemoZero {

    @Autowired
    private ScheduleTestService scheduleTestService;


    @PostMapping("/start")
    public void start (@RequestBody ScheduleSetting scheduleSetting) throws SchedulerException {
        scheduleTestService.startQurtaz(scheduleSetting);
    }

    @PostMapping("/stop")
    public void stop (@RequestBody ScheduleSetting scheduleSetting) throws SchedulerException {
        scheduleTestService.stopQurtaz(scheduleSetting);
    }

    @PostMapping("/restart")
    public void restart (@RequestBody ScheduleSetting scheduleSetting) throws SchedulerException {
        scheduleTestService.restartQurtaz(scheduleSetting);
    }
}
