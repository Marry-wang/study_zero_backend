package com.demo.backend.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.backend.demo.ScheduleSetting;
import com.demo.backend.mapper.ScheduleSettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Component
public class TimmerComponent {
    // 保存任务
    private Map<String, ScheduledFuture<?>> futuresMap = new ConcurrentHashMap<String, ScheduledFuture<?>>();

    @Autowired
    private ScheduleSettingMapper timmerDao;

    // 创建ThreadPoolTaskScheduler线程池
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        return threadPoolTaskScheduler;
    }

    // 初始化任务
    @Bean
    public void initTimmer(){
        List<ScheduleSetting> list = timmerDao.selectList(new QueryWrapper<ScheduleSetting>());
        for (ScheduleSetting s : list){
            ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(getRunnable(s), getTrigger(s));
            futuresMap.put(s.getJobName(), future);
        }
    }

    /**
     * 添加任务
     * @param s
     */
    public void addTask(ScheduleSetting s){
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(getRunnable(s), getTrigger(s));
        futuresMap.put(s.getJobName(), future);
    }

    /**
     * 暂停任务
     * @param key
     * @return
     */
    public boolean pauseeTask(String key) {
        ScheduledFuture toBeRemovedFuture = futuresMap.remove(key);
        if (toBeRemovedFuture != null) {
            toBeRemovedFuture.cancel(true);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 更新任务
     * @param s
     */
    public void updateTask(ScheduleSetting s) {
        ScheduledFuture toBeRemovedFuture = futuresMap.remove(s.getJobName());
        if (toBeRemovedFuture != null) {
            toBeRemovedFuture.cancel(true);
        }
        addTask(s);
    }


    /**
     * 转换首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerFirstCapse(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    /**
     * runnable
     * @param scheduleConfig
     * @return
     */
    private Runnable getRunnable(ScheduleSetting scheduleConfig){
        return new Runnable() {
            @Override
            public void run() {
                Class<?> clazz;
                try {
                    clazz = Class.forName(scheduleConfig.getClassName());
                    String className = lowerFirstCapse(clazz.getSimpleName());
                    Object bean = (Object) ApplicationContextHelper.getBean(className);
                    Method method = ReflectionUtils.findMethod(bean.getClass(), scheduleConfig.getMethod());
                    ReflectionUtils.invokeMethod(method, bean);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * Trigger
     * @param scheduleConfig
     * @return
     */
    private Trigger getTrigger(ScheduleSetting scheduleConfig){
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger trigger = new CronTrigger(scheduleConfig.getCron());
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                return nextExec;
            }
        };

    }

}
