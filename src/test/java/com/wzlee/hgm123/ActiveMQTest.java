package com.wzlee.hgm123;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wzlee.hgm123.controller.SystemController;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={
        "file:src/main/webapp/WEB-INF/spring/*.xml", 
        "file:src/main/webapp/WEB-INF/spring/appServlet/*.xml"})
public class ActiveMQTest {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);
	
	@Autowired
//	private static RabbitTemplate rabbitTemplate;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void sendMessage(){
//		rabbitTemplate.convertAndSend("hello word.");
//		MessageProperties messageProperties = new MessageProperties();
//		messageProperties.setUserId("1");
//		rabbitTemplate.setRoutingKey("foo.helloWorld");
//		rabbitTemplate.send(new Message("Hello World".getBytes(), messageProperties));
	}
	
}
