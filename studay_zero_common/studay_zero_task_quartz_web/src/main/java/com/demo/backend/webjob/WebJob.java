package com.demo.backend.webjob;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@DisallowConcurrentExecution //作业不并发
@Component
public class WebJob implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        System.out.println("欢迎使用yyblog,这是一个定时任务  --小卖铺的老爷爷!");
    }
}
