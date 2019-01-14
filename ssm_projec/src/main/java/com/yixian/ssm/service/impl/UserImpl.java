package com.yixian.ssm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yixian.ssm.mapper.UserMapper;
import com.yixian.ssm.model.User;
import com.yixian.ssm.model.UserExample;
import com.yixian.ssm.service.IUserService;
@Service
public class UserImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> selectAll() {
		UserExample example = new UserExample();
		List<User> list = userMapper.selectByExample(example);
		return list;
	}
	
	

}
