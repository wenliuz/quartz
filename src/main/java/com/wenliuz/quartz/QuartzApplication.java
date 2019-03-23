package com.wenliuz.quartz;

import com.wenliuz.quartz.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class QuartzApplication {

    public static void main(String[] args) {
        //SpringApplication.run(QuartzApplication.class, args);
        //System.out.println("jjj");
        //List list = new ArrayList<>();
        //list.add("aa");
        //定义job
        JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1","group1")
                .usingJobData("key1","hello")
                //.usingJobData(new JobDataMap())
                .build();

        //定义trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(5)
                                .repeatForever()
                )
                .build();

        try {
            //实例化schedule
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //启动schedule
            scheduler.start();
            //使用自定义的触发器开始任务
            scheduler.scheduleJob(job,trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        HolidayCalendar hcd = new HolidayCalendar();


    }

}
