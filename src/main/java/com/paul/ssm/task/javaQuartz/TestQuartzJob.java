package com.paul.ssm.task.javaQuartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestQuartzJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		  System.out.println("==============In TestQuartzJob - executing its JOB at " 
	                + new Date());
	}
	
}
