package com.example.library.configuration;

import com.example.library.quartz.job.SendEmailNotificationJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig
{
	private LibraryProperties libraryProperties;

	private String mailNotificationSchedule;

	@Autowired
	public QuartzConfig(LibraryProperties libraryProperties)
	{
		this.libraryProperties = libraryProperties;
		this.mailNotificationSchedule = this.libraryProperties.getRemainder();
	}

	@Bean
	public JobDetail sendEmailJobDetails()
	{
		return JobBuilder.newJob(SendEmailNotificationJob.class).withIdentity(SendEmailNotificationJob.class.getSimpleName()+" identity").storeDurably().build();
	}

	@Bean
	public Trigger sendEmailJobTrigger(JobDetail sendEmailJobDetails)
	{
		return TriggerBuilder.newTrigger().forJob(sendEmailJobDetails).withIdentity(SendEmailNotificationJob.class.getSimpleName()+" trigger").withSchedule(
				CronScheduleBuilder.cronSchedule(mailNotificationSchedule)).build();
	}
}
