package com.demo.util;

import cn.hutool.extra.mail.MailAccount;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MailUtil {

	
	/**
	 * 定义一个yml配置文件里的静态变量
	 */
    private static String Host = "smtp.163.com";
    private static String Protocol = "smtp";
    private static String EmailUser = "szyxzgfhzh@163.com";
    private static String Auth = "true";
    private static String Timeout = "25000";
	


	public static void main(String[] args) {
		List<String> toEmails = new ArrayList<String>();
		toEmails.add("1730313139@qq.com");
		String html = "<html><head></head><body>您好：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您在LEGAL商务云超市服务平台发起的合同公议完成距今15天，根据系统要求您需要尽快登录到系统中进行归档工作，可登录到商务云超市系统中进行合同归档，超过20日未完成合同归档者系统将暂停合同申报权限。</body></html>";
		html = "您在LEGAL商务云超市服务平台发起的合同公议完成距今15天，根据系统要求您需要尽快登录到系统中进行归档工作，可登录到商务云超市系统中进行合同归档，超过20日未完成合同归档者系统将暂停合同申报权限。";
		try {
			MailUtil util = new MailUtil();
			util.sendEmail(toEmails, "测试", html);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void sendEmail(List<String> toEmails, String subject, String htmlText) throws Exception {
		System.out.println("---------------------sendEmail-----------------------");
//		Properties props = new Properties();
//		props.setProperty("mail.transport.protocol", Protocol);
//		props.setProperty("mail.smtp.timeout", Timeout);
//		props.setProperty("mail.host", Host);
//		props.setProperty("mail.user", "");
//		props.setProperty("mail.password", "");
//		props.setProperty("mail.smtp.auth", Auth);
//
//
//		Session mailSession = Session.getDefaultInstance(props, null);
//		Transport transport = mailSession.getTransport();
//		MimeMessage message = new MimeMessage(mailSession);
//		message.setSubject(subject);
//		message.setFrom(new InternetAddress(EmailUser));
//
//		InternetAddress[] Email = new InternetAddress[toEmails.size()];
//		Iterator<String> it = toEmails.iterator();
//		for (int i = 0; i < toEmails.size(); i++) {
//			Email[i] = new InternetAddress(it.next());
//		}
//
//		message.addRecipients(Message.RecipientType.TO, Email);
//		MimeMultipart multipart = new MimeMultipart("related");
//
//		// first part (the html)
//		BodyPart messageBodyPart = new MimeBodyPart();
//		messageBodyPart.setContent(htmlText, "text/html;charset=UTF-8");
//		// add it
//		multipart.addBodyPart(messageBodyPart);
//
//		// put everything together
//		message.setContent(multipart);
//		transport.connect("smtp.163.com", EmailUser, "LGBLEOURQMWRNEZM");
//		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
//		System.out.println("---------------------发送成功-----------------------");
//		transport.close();

		MailAccount mailAccount =new MailAccount();
		mailAccount.setHost(Host);
		mailAccount.setAuth(true);
		mailAccount.setFrom(EmailUser);
		mailAccount.setPass("LGBLEOURQMWRNEZM");
		cn.hutool.extra.mail.MailUtil.send(mailAccount, toEmails,subject,htmlText,true);
	}

}