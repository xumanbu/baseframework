package com.cxypub.baseframework.sdk.jms;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息的异步被动接收者.
 * 
 * 使用Spring的MessageListenerContainer侦听消息并调用本Listener进行处理.
 * 
 * @author calvin
 *
 */
public class NotifyMessageListener implements MessageListener {

	private static Logger logger = LoggerFactory.getLogger(NotifyMessageListener.class);

	// @Autowired(required = false)
	// private SimpleMailService simpleMailService;

	/**
	 * MessageListener回调函数.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void onMessage(Message message) {
		System.out.println("a message is recived");
		try {
			if (message instanceof ActiveMQObjectMessage) {
				ActiveMQObjectMessage mapMessage = (ActiveMQObjectMessage) message;
				try {
					System.out.println(mapMessage.getDestination().toString());
					List<JmsMessage> list = (List<JmsMessage>) mapMessage.getObject();
					for (JmsMessage jmsMessage : list) {
						System.out.println("`````````````````````````````" + jmsMessage.getTitle());

					}
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// SendShortMessage.sendOut_differentcontent(content);
		} catch (Exception e) {
			logger.error("处理消息时发生异常.", e);
		}
	}
}
