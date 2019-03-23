package com.wenliuz.quartz.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author wenliuz
 */
public class MyJob implements Job {

    ArrayList state;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("hello world!");
        JobKey key = context.getJobDetail().getKey();
        System.out.println("job key:" + key);
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        //JobDataMap dataMap = context.getMergedJobDataMap();
        System.out.println(dataMap.getString("key1"));
        //ArrayList state = (ArrayList)dataMap.get("myStateData");
        //state.add(new Date());

    }

    public ArrayList getState() {
        return state;
    }

    public void setState(ArrayList state) {
        this.state = state;
    }
}
