package com.pancm.web;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.pancm.pojo.Mail;

/**
 * 
* @Title: HelloWorldController
* @Description: 邮件发送测试
* 使用post 请求即可发送 邮件 
url: :http://localhost:8182/api/mail/send
参数:：{
	"sender":"xxx@qq.com",
	"receiver":"xxx@qq.com",
	"subject":"测试主题",
	"text":"测试消息"	
}

* @Version:1.0.0  
* @author pancm
* @date 2019年1月3日
 */
@RestController
@RequestMapping("/api/mail")
public class MailController {
	private static Logger LOG=LoggerFactory.getLogger(MailController.class);
	
	@Autowired
	private JavaMailSender mailSender;
     
	@Autowired
    private TemplateEngine templateEngine;
	/*
	 * 发送普通邮件
	 */
    @PostMapping("/sendMail")
    public String sendMail(@RequestBody Mail mail) {
    	SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mail.getSender());
		message.setTo(mail.getReceiver());
		message.setSubject(mail.getSubject());
		message.setText(mail.getText());
		mailSender.send(message);
		LOG.info("发送成功!");
        return "发送成功！";
    }
    
    
    /*
     *  发送附件
     */
    @PostMapping("/sendAttachments")
    public String sendAttachmentsMail(@RequestBody Mail mail) throws MessagingException  {

    	MimeMessage mimeMessage = mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    	helper.setFrom(mail.getSender());
    	helper.setTo(mail.getReceiver());
    	helper.setSubject(mail.getSubject());
    	helper.setText(mail.getText());
    	FileSystemResource file = new FileSystemResource(new File("1.png"));
    	helper.addAttachment("附件.jpg", file);
    	mailSender.send(mimeMessage);
		return "发送成功!";

    }
    
    /*
     * 发送静态资源
     */
    @PostMapping("/sendInlineMail")
    public String sendInlineMail(@RequestBody Mail mail) throws Exception {

    	MimeMessage mimeMessage = mailSender.createMimeMessage();

    	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    	helper.setFrom(mail.getSender());
    	helper.setTo(mail.getReceiver());
    	helper.setSubject(mail.getSubject());
    	//这里的text 是html
    	helper.setText(mail.getText(), true);
    	FileSystemResource file = new FileSystemResource(new File("1.png"));
    	helper.addInline("文件", file);
    	mailSender.send(mimeMessage);
    	return "发送成功!";
    }
    
    
    /*
     * 发送静态资源
     */
    @PostMapping("/sendTemplateMail")
    public void sendTemplateMail(@RequestBody Mail mail) throws Exception {

    	MimeMessage mimeMessage = mailSender.createMimeMessage();

    	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    	helper.setFrom(mail.getSender());
    	helper.setTo(mail.getReceiver());
    	helper.setSubject(mail.getSubject());
    	
    	
    	//创建邮件正文
        Context context = new Context();
        context.setVariable("id", "1");
        context.setVariable("name", "xuwujing");
        String emailContent = templateEngine.process("emailTemplate", context);
    	helper.setText(emailContent, true);
    	mailSender.send(mimeMessage);
    }
    
}
