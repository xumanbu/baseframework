package com.cxypub.baseframework.sdk.action.spring;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cxypub.baseframework.sdk.security.base.AuthUser;

/**
 * @ClassName: BaseController
 * @Description: 控制器的基类,用于存放control层中公共的基础方法
 * @date 2015-5-14 上午9:36:36
 */
public abstract class BaseController {

	/**
	 * log日志对象，向system.out说拜拜吧:-D
	 * 本系统默认日志级别是info:{eg:log.info("hello,oao!")}
	 */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 一次http请求返回中request对象，方便以后的不时之需
	 */
	protected HttpServletRequest request;

	/**
	 * 一次http请求返回中response对象，方便以后的不时之需
	 */
	protected HttpServletResponse response;

	/**
	 * 处理成功ajax返回的字符串
	 */
	public static final String SUCCESS = "success";

	/**
	 * 处理成功ajax返回的字符串,表单唯一校验的时候成功的标识是pass，建议同一-_-!
	 */
	public static final String PASS = "pass";

	/**
	 * 处理失败ajax返回的字符串
	 */
	public static final String FAIL = "fail";

	/**
	 * 错误页面
	 */
	public static final String E_405 = "error/405";

	public static final String E_404 = "error/404";

	public static final String E_500 = "error/500";

	/**
	 * 表单操作类型和参数
	 */
	public static final String ACT_TYPE = "actType";

	public static final String ACT_TYPE_OK = "OK";

	public static final String ACT_TYPE_ERROR = "ERROR";

	// 设置是否使用字符串安全转义
	public boolean isSafeString() {
		return true;
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		if (isSafeString()) {
			binder.registerCustomEditor(String.class, new StringEscapeEditor(true));// 字符专意
		}
	}

	@ModelAttribute
	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	// 从shiro中获取当前用户id
	protected String getUserId() {
		AuthUser user = this.getUser();
		if (user != null) {
			return user.getLoginToken().getId();
		} else {
			return null;
		}
	}

	// 从shiro中获取当前用户对象
	protected AuthUser getUser() {
		return null;
	}

	// 通过response直接输出客户端，使用这个方法，controller中的方法返回值为void
	protected void out(String outString) {
		PrintWriter writer = null;
		try {
			writer = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	// ajax请求回送请求错误的状态
	protected void sendError() {
		try {
			WebUtils.toHttp(response).sendError(500);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: getSession
	 * @Description: 获得Shiro的session
	 * @return
	 * @author 徐飞
	 */
	protected Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}
}
