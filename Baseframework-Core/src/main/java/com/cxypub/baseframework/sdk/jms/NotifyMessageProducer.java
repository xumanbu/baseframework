package com.cxypub.baseframework.sdk.jms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;

import org.springframework.jms.core.JmsTemplate;

/**
 * JMS用户变更消息生产者.
 * 
 * 使用jmsTemplate将用户变更消息分别发送到queue与topic.
 * 
 * @author calvin
 */
public class NotifyMessageProducer {
	private JmsTemplate jmsTemplate;

	private Destination notifyQueue;

	private Destination notifyTopic;

	public void sendQueue(final List<JmsMessage> list) {
		sendMessage(list, notifyQueue);
	}

	public void sendTopic(final List<JmsMessage> list) {
		// Destination topic = new ActiveMQTopic("t.notify."+topicId);

		sendMessage(list, notifyTopic);
	}

	/**
	 * 使用jmsTemplate最简便的封装convertAndSend()发送Map类型的消息.
	 */
	private void sendMessage(List<JmsMessage> list, Destination destination) {
		Map<String, List<JmsMessage>> map = new HashMap<String, List<JmsMessage>>();
		map.put("content", list);

		jmsTemplate.convertAndSend(destination, list);
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setNotifyQueue(Destination notifyQueue) {
		this.notifyQueue = notifyQueue;
	}

	public void setNotifyTopic(Destination notifyTopic) {
		this.notifyTopic = notifyTopic;
	}

}
