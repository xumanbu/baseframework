
package com.cxypub.baseframework.sdk.jms;

import java.io.Serializable;



/**
 * 
 * @ClassName: JmsMessage
 * @Description: 消息对象  可扩展
 * @author Comsys-陈鹏
 * @date 2015-5-18 下午03:58:21
 *
 */
public class JmsMessage implements Serializable{

	
	
	/**
	 */
	
	private static final long serialVersionUID = 8909523015884221634L;
	private String id;           //消息id
	private String title;        //标题
	private String sendTime;     //发送时间
	private String senderUserId; //发送用户id
	private String inceptUserId; //接受用户id
	private String conten;       //消息内容
	private String url;          //链接地址
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getSenderUserId() {
		return senderUserId;
	}
	public void setSenderUserId(String senderUserId) {
		this.senderUserId = senderUserId;
	}
	public String getInceptUserId() {
		return inceptUserId;
	}
	public void setInceptUserId(String inceptUserId) {
		this.inceptUserId = inceptUserId;
	}
	public String getConten() {
		return conten;
	}
	public void setConten(String conten) {
		this.conten = conten;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
