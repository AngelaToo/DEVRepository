package com.yixian.ssm.service.impl;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.yixian.common.utils.CollectionUtils;
import com.yixian.core.dfs.OssFileUtils;
import com.yixian.ssm.service.IMailSendService;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 邮件发送服务实现
 *
 * @param
 * @author guobin
 * @return
 * @date 2018-09-05 19:31
 */
@Service
public class MailSendServiceImpl implements IMailSendService {

	private static final Logger logger = LoggerFactory.getLogger(MailSendServiceImpl.class);
	private static final String SUBJECT = "祝您中秋快乐！";

	@Resource
	private JavaMailSenderImpl mailSender;

	@Resource
	private Executor taskExecutor;

	@Resource
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Override
	public void sendSimpleMessage(String message, String mail) {
		sendMail(mail, null, null, message);
	}

	@Override
	public void sendSimpleMessage(String subject, String message, String mail) {
		sendMail(mail, subject, null, message);
	}

	@Override
	public void sendAttachmentMessage(String[] files, String message, String mail) {
		sendMail(mail, null, files, message);
	}

	@Override
	public void sendAttachmentMessage(String subject, String[] files, String message, String mail) {
		sendMail(mail, subject, files, message);
	}

	@Override
	public void sendTampleteMessage(String ftl, Map<String, Object> param, String mail) {
		String text = getTemplate(ftl, param);
		sendMail(mail, null, null, text);
	}

	@Override
	public void sendTampleteMessage(String subject, String ftl, Map<String, Object> param, String mail) {
		String text = getTemplate(ftl, param);
		sendMail(mail, subject, null, text);
	}

	/**
	 * 
	 *
	 * @param ftl
	 * @param param
	 * @return
	 */
	private String getTemplate(String ftl, Map<String, Object> param) {
		String text = "";
		try {
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(ftl);
			text = FreeMarkerTemplateUtils.processTemplateIntoString(template, param);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return text;
	}

	public void sendMail(final String to, final String subject, final String[] files, final String content) {
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				asynSendMail(subject, to, content, files);
				logger.debug("邮件已发送...");
			}
		});
	}

	private void asynSendMail(String subject, String to, String content, String[] files) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(mailSender.getUsername());
			if (subject != null) {
				messageHelper.setSubject(subject);
			} else {
				messageHelper.setSubject(SUBJECT);
			}
			messageHelper.setTo(to);
			messageHelper.setText(content, true);

			if (CollectionUtils.isNotEmpty(files)) {
				for (String file : files) {
					String fileName = file.substring(file.lastIndexOf(File.separator) + 1);
					final byte[] bytes = OssFileUtils.readFileToByteArray(file);
					messageHelper.addAttachment(fileName, new ByteArrayResource(bytes));
				}
			}

			messageHelper.setSentDate(new Date());
			messageHelper.setValidateAddresses(true);
			logger.debug("邮件正在发送...");
			mailSender.send(message);
		} catch (Exception e) {
			logger.error("send mail failed" + e);
			e.printStackTrace();
		}
	}
}
