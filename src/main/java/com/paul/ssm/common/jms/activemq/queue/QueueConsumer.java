package com.paul.ssm.common.jms.activemq.queue;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Service;

/**
 * 队列点对点模式消费者
 * 
 * @author huangyun
 *
 */
@Service
public class QueueConsumer implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		TextMessage tm = (TextMessage)message;
		System.out.println("监听到MQ中有数据......");
		try {			
			System.out.println("获取MQ中数据信息>>>>>>>>>>" +  tm.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
