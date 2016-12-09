package org.hrSolution.daemon.config;

import java.util.HashMap;
import java.util.Map;

import org.hrSolution.daemon.job.UpdateCacheJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

public class QuartzConfiguration {
	
	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean(){
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(UpdateCacheJob.class);
		factory.setGroup("UpdateCacheGroup");
		factory.setName("UCJob");
		return factory;
	}
	//Job is scheduled after every 10 sec 
	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean(){
		CronTriggerFactoryBean ctFactory = new CronTriggerFactoryBean();
		ctFactory.setJobDetail(jobDetailFactoryBean().getObject());
		ctFactory.setStartDelay(3000);
		ctFactory.setName("UpdateCacheTrigger");
		ctFactory.setGroup("UpdateCacheGroup");
		ctFactory.setCronExpression("0/10 * * * * *");
		return ctFactory;
	}
	
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(cronTriggerFactoryBean().getObject());
		return scheduler;
	} 
	
}
