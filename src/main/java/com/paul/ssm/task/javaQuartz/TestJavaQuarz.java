package com.paul.ssm.task.javaQuartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * java + quartz
 * @author huangyun
 *
 */
public class TestJavaQuarz {
	public static void main(String[] args) {
		try {
			// 获取调度器
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			
			// 创建任务器：定义任务细节
			JobDetail jobDetail = JobBuilder.newJob(TestQuartzJob.class).withIdentity("job1", "group1").build();
			ScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5)
					.repeatForever();
			
			// 定义触发器
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "simpleTriggerGroup")
					.withSchedule(scheduleBuilder).startNow().build();

			// 将任务和触发器注册到调度器中
			scheduler.scheduleJob(jobDetail, trigger);
			//Thread.sleep(1000 * 30);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
