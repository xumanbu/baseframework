package com.cxypub.jms.test;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cxypub.baseframework.sdk.jms.JmsMessage;
import com.cxypub.baseframework.sdk.jms.NotifyMessageProducer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jms2.xml")
public class JMSTest2 {

	@Autowired
	NotifyMessageProducer notifyMessageProducer;

	@Autowired
	JmsTemplate template;

	@Autowired
	Destination notifyQueue;

	@Before
	public void init() {
	}

	@Test
	public void sendMessage() {
		template.send(notifyQueue, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage();
				JmsMessage message1 = new JmsMessage();
				message1.setConten("msg1");
				message1.setTitle("hello1");
				objectMessage.setObject(message1);
				return objectMessage;
			}
		});
		System.out.println("成功发送了一条JMS消息");
	}

	@Test
	public void test1() {
		List<JmsMessage> list = new ArrayList<JmsMessage>();
		for (int i = 0; i < 1000; i++) {
			JmsMessage message1 = new JmsMessage();
			message1.setConten("msg1");
			message1.setTitle("hello1");
			list.add(message1);
		}
		for (int i = 0; i < 1; i++) {
			notifyMessageProducer.sendQueue(list);
			notifyMessageProducer.sendTopic(list);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
