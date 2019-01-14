package com.yixian.ssm.test;

import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.yixian.ssm.baseTest.SpringTestCase;
import com.yixian.ssm.model.User;
import com.yixian.ssm.service.IUserService;

public class UserServiceTest extends SpringTestCase	{
	
	@Autowired
	private IUserService userService;
	Logger logger = Logger.getLogger(UserServiceTest.class);
	
	@Test
	public void selectUser(){
		List<User> list = userService.selectAll();
		for (User user : list) {
			System.out.println(user.getUsername()+"****"+user.getEmail());
		}
	}
	
 
}
