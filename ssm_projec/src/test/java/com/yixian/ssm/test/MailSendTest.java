package com.yixian.ssm.test;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.yixian.ssm.baseTest.SpringTestCase;
import com.yixian.ssm.service.IMailSendService;

/**
 * 发送邮件测试类
 *
 * @author guobin
 * @date 2018-09-05 20:50
 */

public class MailSendTest extends SpringTestCase {

	@Resource
	private IMailSendService mailSendService;

	@Test
	public void mailTest(){

		//mailSendService.sendSimpleMessage("你好啊", "2236488862@qq.com");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nickName", "南儿");
		mailSendService.sendTampleteMessage("mail.ftl", map ,"1437825849@qq.com");

		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
