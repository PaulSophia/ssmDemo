package com.paul.ssm.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.paul.ssm.common.jms.activemq.MqQueueSender;

@RunWith(SpringJUnit4ClassRunner.class)  //表示继承了SpringJUnit4ClassRunner类  
//告诉junit spring配置文件
@ContextConfiguration(locations="classpath:spring.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class ActiveMQTest {
	
	@Autowired
	private MqQueueSender mqQueueSender;
	
    @Test
	public void test() {
    	for (int i = 0; i < 5; i ++) {
    		mqQueueSender.send("helloMq", "helloMq send msg" + i);
    	}
    	
    	for (int i = 0; i < 5; i ++) {
    		mqQueueSender.send("helloMq2", "helloMq2 send msg" + i);
    	}
	}
    
    
    public static void main(String[] args) {
    	LocalDateTime localDateTime = LocalDateTime.parse("2018-10-20T17:44:34.156");
    	
    	ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(date));//Tue Mar 27 14:17:17 CST 201

	}
}
