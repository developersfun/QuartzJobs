package og.nrj.mvc;


import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import og.nrj.mvc.jobs.SecondJob;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class MvcProjectApplication {

	public static void main(String[] args) throws SchedulerException {
		
		JobDetail jd = JobBuilder.newJob(SecondJob.class)
				.withIdentity("myJob", "group1")
				.usingJobData("jobSays", "Hello World!")
				.usingJobData("myFloatValue", 3.141f)
				.build();
		
		/*
		 * SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
		 * .withIdentity("trigger2", "group1") .startAt(new
		 * Date(System.currentTimeMillis()))
		 * .withSchedule(SimpleScheduleBuilder.simpleSchedule()
		 * .withIntervalInSeconds(10) .withRepeatCount(10)) .forJob("job1") .build();
		 */
		
		
		
		  CronTrigger trigger = TriggerBuilder.newTrigger() .withIdentity("trigger3",
		  "group1") .withSchedule(CronScheduleBuilder.cronSchedule("0/5 0 0 ? * * *"))
		  .forJob("myJob", "group1") .build();
		 
		
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.scheduleJob(jd, trigger);
		scheduler.start();
		
		
		
		
		
		
		SpringApplication.run(MvcProjectApplication.class, args);
	}

}
