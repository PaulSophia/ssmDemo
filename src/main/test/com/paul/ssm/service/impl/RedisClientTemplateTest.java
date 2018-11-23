package com.paul.ssm.service.impl;

import java.util.Calendar;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.paul.ssm.common.cache.redis.RedisClientTemplate;

import junit.framework.Assert;
import redis.clients.jedis.Tuple;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)  //表示继承了SpringJUnit4ClassRunner类  
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class RedisClientTemplateTest {
	
	@Autowired
    public RedisClientTemplate redisClientTemplate;
	
    
	public void testInsert() {
    	String realName = "huangyun";
		redisClientTemplate.set("paul", "huangyun");
		String value = redisClientTemplate.get("paul");
		System.out.println("value:" + value);
		
		Assert.assertEquals(realName, value);
        //
	}
    
	@Test
    public void testDelayMessage() throws Exception {
    	this.preduceDelayMsg();
    	this.consumeDelayMsg();
    }
	
	private void preduceDelayMsg() throws Exception {
		for (int i = 0; i < 1; i ++) {
			Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.SECOND, 10);
			
			int second5later = (int) (cal1.getTimeInMillis() / 1000);
			System.out.println("second5later:" + second5later);
			redisClientTemplate.zadd("OrderId", second5later, "OID0000001" + i);
			
			System.out.println(System.currentTimeMillis()+"ms:redis生成了一个订单任务：订单ID为"+"OID0000001"+i);
			//Thread.sleep(2000);
		}
	}
	
	private void consumeDelayMsg() {
		while(true){
            Set<Tuple> items = redisClientTemplate.zrangeWithScores("OrderId", 0, 1);
            if(items == null || items.isEmpty()){
                System.out.println("当前没有等待的任务");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            int  score = (int) ((Tuple)items.toArray()[0]).getScore();
            Calendar cal = Calendar.getInstance();

            int nowSecond = (int) (cal.getTimeInMillis() / 1000);
            if(nowSecond >= score){
                String orderId = ((Tuple)items.toArray()[0]).getElement();
                redisClientTemplate.zrem("OrderId", orderId);
                System.out.println(System.currentTimeMillis() +"ms:redis消费了一个任务：消费的订单OrderId为"+orderId);
            }
        }
	}
}
