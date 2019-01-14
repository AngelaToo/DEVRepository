package com.yixian.ssm.service;

import java.util.Map;

/**
 * 邮件发送服务
 *
 * @author guobin
 * @date 2018-09-05 17:05
 */
public interface IMailSendService {

	/**
	 * 发送简单文本邮件
	 *
	 * @param message 邮件内容
	 * @param mail    接受者邮箱
	 * @author guobin
	 * @date 2018-09-05 17:04
	 */
	void sendSimpleMessage(String message, String mail);

	/**
	 * 发送简单文本邮件
	 *
	 * @param subject 邮箱主题
	 * @param message 邮件内容
	 * @param mail    接受者邮箱
	 * @return
	 * @author guobin
	 * @date 2018-09-06 10:08
	 */
	void sendSimpleMessage(String subject, String message, String mail);

	/**
	 * 发送附件邮件
	 *
	 * @param files   附件OSS文件地址
	 * @param message 邮件内容
	 * @param mail    接受者邮箱
	 * @return
	 * @author guobin
	 * @date 2018-09-05 17:50
	 */
	void sendAttachmentMessage(String[] files, String message, String mail);


	/**
	 * 发送附件邮件
	 *
	 * @param subject 邮箱主题
	 * @param files   附件OSS文件地址
	 * @param message 邮件内容
	 * @param mail    接受者邮箱
	 * @return
	 * @author guobin
	 * @date 2018-09-06 10:07
	 */
	void sendAttachmentMessage(String subject, String[] files, String message, String mail);

	/**
	 * 发送模版邮件
	 *
	 * @param ftl   freeMarker模版文件ftl 相对路径
	 * @param param 模版对应参数
	 * @param mail  接受者邮箱
	 * @return
	 * @author guobin
	 * @date 2018-09-05 17:51
	 */
	void sendTampleteMessage(String ftl, Map<String, Object> param, String mail);


	/**
	 * 发送模版邮件
	 *
	 * @param subject 邮箱主题
	 * @param ftl     freeMarker模版文件ftl 相对路径
	 * @param param   模版对应参数
	 * @param mail    接受者邮箱
	 * @return
	 * @author guobin
	 * @date 2018-09-06 10:06
	 */
	void sendTampleteMessage(String subject, String ftl, Map<String, Object> param, String mail);
}
