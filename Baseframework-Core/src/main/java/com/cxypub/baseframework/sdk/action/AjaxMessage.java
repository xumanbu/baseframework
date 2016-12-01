package com.cxypub.baseframework.sdk.action;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author wangyan
 * @date 2014-11-06 22:44
 */
public final class AjaxMessage {

	/**
	 * 错误信息
	 */
	public static final int ERROR = 0;

	/**
	 * 警告信息
	 */
	public static final int WARN = -1;

	/**
	 * 普通信息
	 */
	public static final int INFO = 1;

	/**
	 * 类型
	 */
	private int type;

	/**
	 * 内容
	 */
	private String msg;

	/**
	 * 其他信息
	 */
	private Map<String, Object> infos = Maps.newHashMap();

	/**
	 * ajax信息构造器
	 * @param msg 返回信息
	 */
	private AjaxMessage(String msg) {
		this.msg = msg;
		this.type = INFO;
	}

	/**
	 * ajax信息构造器
	 * @param type 信息类型
	 * @param msg 返回信息
	 */
	private AjaxMessage(int type, String msg) {
		this.type = type;
		this.msg = msg;
	}

	/**
	 * ajax信息构造器
	 * @param type 信息类型
	 * @param msg 返回信息
	 * @param infos 其他信息
	 */
	private AjaxMessage(int type, String msg, Map<String, Object> infos) {
		this.type = type;
		this.msg = msg;
		this.infos = infos;
	}

	/**
	 * @Title: success
	 * @Description: 返回一个成功信息
	 * @param msg
	 * @author 徐飞
	 */
	public static AjaxMessage success(String msg) {
		return new AjaxMessage(msg);
	}

	/**
	 * @Title: error
	 * @Description: 返回一个错误的信息
	 * @param msg
	 * @return  传人参数
	 * @author 徐飞
	 */
	public static AjaxMessage error(String msg) {
		return new AjaxMessage(AjaxMessage.ERROR, msg);
	}

	/**
	 * 获取ajaxMessage对象
	 * @param type 类型
	 * @param msg 信息
	 * @return ajaxMessage对象
	 */
	public static AjaxMessage getInstance(int type, String msg) {
		return new AjaxMessage(type, msg);
	}

	/**
	 * 获取ajaxMessage对象
	 * @param type 类型
	 * @param msg 信息
	 * @param infos 其他信息
	 * @return ajaxMessage对象
	 */
	public static AjaxMessage getInstance(int type, String msg, Map<String, Object> infos) {
		return new AjaxMessage(type, msg, infos);
	}

	/**
	 * 获取type
	 * @return type
	 */
	public int getType() {
		return type;
	}

	/**
	 * 设置type
	 * @param type type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * 获取msg
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 设置msg
	 * @param msg msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 获取infos
	 * @return infos
	 */
	public Map<String, Object> getInfos() {
		return infos;
	}

	/**
	 * 设置infos
	 * @param infos infos
	 */
	public void setInfos(Map<String, Object> infos) {
		this.infos = infos;
	}
}
