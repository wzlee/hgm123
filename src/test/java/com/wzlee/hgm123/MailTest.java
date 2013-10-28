package com.wzlee.hgm123;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/*.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/*.xml" })
public class MailTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void TextMail() throws EmailException {
		Email email = new SimpleEmail();
		email.setDebug(true);
		email.setHostName("smtp.qq.com");
		email.setSmtpPort(25);
		email.setAuthenticator(new DefaultAuthenticator("214508914", "123@456"));
		email.setSSLOnConnect(true);
		email.setFrom("214508914@qq.com");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("214508914@qq.com");
		email.send();
	}

	@Test
	public void AttachmentMail() throws EmailException {
		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("src/main/webapp/resources/images/hgm123_logo_s.png");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Picture of John");
		attachment.setName("Hgm123.png");

		// Create the email message
		
		MultiPartEmail email = new MultiPartEmail();
		email.setDebug(true);
		email.setHostName("smtp.qq.com");
		email.setAuthenticator(new DefaultAuthenticator("214508914", "123@456"));
		email.setSSLOnConnect(true);
		email.addTo("214508914@qq.com", "Player");
		email.setFrom("214508914@qq.com","Hgm123");
		email.setSubject("The picture");
		email.setMsg("Here is the picture you wanted");

		// add the attachment
		email.attach(attachment);

		// send the email
		email.send();
	}

	@Test
	public void HTMLMail() throws EmailException, MalformedURLException {
		// Create the email message
		HtmlEmail email = new HtmlEmail();
		email.setDebug(true);
		email.setCharset("utf-8");
		email.setHostName("smtp.exmail.qq.com");
		email.setSSLOnConnect(true);
		email.setAuthenticator(new DefaultAuthenticator("admin@hgm123.com", "loveu1314"));
		email.addTo("214508914@qq.com");
		email.setFrom("admin@hgm123.com","Hgm123管理员");
		email.setSubject("Hgm123通行证激活邮件<系统自动发出,请勿回复>");

		// embed the image and get the content id
		URL logo_url = new URL("http://www.hgm123.com/resources/images/hgm123_logo_r.png");
		String logo_src = email.embed(logo_url, "Apache logo");

		// set the html message
		email.setHtmlMsg("<html>The apache logo - <img src=\"cid:" + logo_src + "\">HGM123,最专业,最权威的游戏资讯发布平台</html>");

		// set the alternative message
		email.setTextMsg("Your email client does not support HTML messages");

		// send the email
		email.send();

	}
}
