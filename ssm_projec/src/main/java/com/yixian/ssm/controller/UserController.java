package com.yixian.ssm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yixian.ssm.model.User;
import com.yixian.ssm.service.IUserService;
 
/**
 * 功能概要：UserController
 */
@Controller
@RequestMapping("/user/*") 
public class UserController {
	@Resource
	private IUserService userService;
	
	@RequestMapping("/queryList")
    public ModelAndView getIndex(ModelAndView model){ 
		List<User> list = userService.selectAll();
		model.addObject("list", list);
		return model;
    }  
}

