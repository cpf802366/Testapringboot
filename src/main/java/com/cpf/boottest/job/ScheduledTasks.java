package com.cpf.boottest.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cpf on 2017/9/1.
 */
/*@Component*/
public class ScheduledTasks {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron="0/5 * * * * ?") //每分钟执行一次
    public void reportCurrentTime() {
        System.out.println("The time is now {}" + dateFormat.format(new Date()));
    }
}
