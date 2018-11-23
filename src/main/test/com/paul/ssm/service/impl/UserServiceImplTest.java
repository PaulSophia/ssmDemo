package com.paul.ssm.service.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.paul.ssm.common.util.MD5Utils;
import com.paul.ssm.domain.User;
import com.paul.ssm.service.IUserService;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)  //表示继承了SpringJUnit4ClassRunner类  
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring.xml"})
//@ContextConfiguration(locations="classpath:spring.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserServiceImplTest {

	@Autowired
    public IUserService userService;
    
    @Test
	public void testInsert() {
    	User user = new User();
    	//user.setId(105L);
    	user.setUserName("paul");
    	user.setPassword(MD5Utils.md5("123456"));
    	user.setName("黄云");
    	user.setMobile("1828428987");
    	user.setDeleted(false);
    	user.setCreateTime(new Date());
    	user.setUpdateTime(new Date());
    	
		int i = userService.insert(user);
        System.out.println(user.getUserName());
	}

}
