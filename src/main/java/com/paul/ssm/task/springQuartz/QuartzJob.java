package com.paul.ssm.task.springQuartz;

/**
 * 任务类(普通pojo类)
 * @author huangyun
 *
 */
public class QuartzJob {
	public void run() {
		System.out.println("开启QuartzJob定时任务!!");
	}
}
