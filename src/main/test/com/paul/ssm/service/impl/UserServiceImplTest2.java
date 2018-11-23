package com.paul.ssm.service.impl;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.paul.ssm.common.util.MD5Utils;
import com.paul.ssm.domain.User;
import com.paul.ssm.service.IUserService;

public class UserServiceImplTest2 {

	public static void main(String[] args) {
		ApplicationContext application = new ClassPathXmlApplicationContext("spring.xml");
		
		IUserService uService = (IUserService) application.getBean("userService");
		
		User user = new User();
    	user.setId(123456L);
    	user.setUserName("paul");
    	user.setPassword(MD5Utils.md5("123456"));
    	user.setName("黄云");
    	user.setMobile("1828428987");
    	user.setDeleted(false);
    	user.setCreateTime(new Date());
    	user.setUpdateTime(new Date());
    	
        int i = uService.insert(user);
        System.out.println(user.getUserName() + i);
	}
}
