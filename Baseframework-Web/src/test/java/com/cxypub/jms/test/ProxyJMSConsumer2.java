package com.cxypub.jms.test;

import javax.jms.Destination;
import javax.jms.ObjectMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/**
 * JMS消费者
 * 消息题的内容定义
 * 消息对象 接收消息对象后： 接收到的消息体* <p> 
 */
public class ProxyJMSConsumer2 {

	ApplicationContext applicationContext;

	Destination destination;

	JmsTemplate jmsTemplate;

	public ProxyJMSConsumer2() {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext-jms2.xml");
		jmsTemplate = ((JmsTemplate) applicationContext.getBean("jmsTemplate"));
		destination = (Destination) applicationContext.getBean("notifyQueue");
	}

	/**
	 * 监听到消息目的有消息后自动调用onMessage(Message message)方法
	 */
	public void recive() {
		while (true) {
			try {
				ObjectMessage txtmsg = (ObjectMessage) jmsTemplate.receive(destination);
				if (null != txtmsg) {
					System.out.println("clientB = ############# revice a message : " + txtmsg);
				} else {
					break;
				}
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {
		ProxyJMSConsumer2 proxyJMSConsumer = new ProxyJMSConsumer2();
		proxyJMSConsumer.recive();
	}

}