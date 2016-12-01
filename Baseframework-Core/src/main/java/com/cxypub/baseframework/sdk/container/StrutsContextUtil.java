package com.cxypub.baseframework.sdk.container;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

/**
 * <b>类名</b>：StrutsContextUtil.java<br>
 *
 * <p><b>标题</b>：品恩产品研发  </p>
 * <p><b>描述</b>：
 *           获取struts容器相关信息
 * </p>
 * <p><b>版权声明</b>：Copyright (c) 2012</p>
 * <p><b>公司</b>：品恩科技 </p>
 * @author <font color='blue'>liucong</font> 
 * @version 1.0
 * @date  2012-9-27 上午10:16:38
 */
public class StrutsContextUtil {

	@SuppressWarnings("unused")
	private static ServletContext sc = null;

	/**
	 * 获取部署的WebApp相应资源实际路径
	 * 
	 * @param resource
	 * @return
	 */
	public static String getRealPath(String resource) {
		return getServletContext().getRealPath(resource);
	}

	/**
	 * 获取部署的Webapp上下文路径
	 * 
	 * @return ctx
	 */
	public static String getContextPath() {
		return getServletContext().getContextPath();
	}

	/**
	 * 获取ServletContext的内容
	 * 
	 * @param arg0
	 * @return
	 */
	public static Object getServletContextAttribute(String arg0) {
		// return ServletActionContext.getServletContext().getAttribute(arg0);
		return getServletContext().getAttribute(arg0);
	}

	/**
	 * 获取全局上下文ServletContext。
	 * 
	 * @return servletContext
	 * @author huangb
	 * @date 2011-03-03
	 */
	public static ServletContext getServletContext() {
		// return ServletActionContext.getServletContext();
		return ServletActionContext.getServletContext();
	}

	/**
	 * <b>方法名</b>：setServletContext<br>
	 * <b>功能</b>：设置全局的<br>
	 * @author <font color='blue'>Administrator</font> 
	 * @date  2011-12-19 下午05:45:46
	 */
	public static void setServletContext(ServletContext sc) {
		StrutsContextUtil.sc = sc;
	}

	/**
	 * @description 获取真是的客户端的IP 一般情况直接用request.getRemoteAddr()方法
	 *              获取客户端的IP地址，但是如果客户端使用了代理服务器，该方法得到的是 代理服务器的地址，不是客户端的地址。
	 * 
	 *              代理服务器在转发客户端的请求时，会在HTTP的头消息中增加"x-forwarded-for"
	 *              信息，该信息中就保存有原客户端的地址。通过request的getHeader方法得
	 *              HTTP头中的"x-forwarded-for"信息，便可以获得客户端的真实IP。
	 * 
	 *              当客户端通过多级代理访问时，"x-forwarded-for"信息中的第一个非unknown字符串 即为客户端的真实IP
	 * @date 2010-7-12
	 * @author liuhh
	 * @return 返回真实的IP地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = null;
		remoteAddr = request.getHeader("x-forwarded-for");
		if (remoteAddr != null && remoteAddr.length() != 0) {
			while ((remoteAddr != null) && (remoteAddr.equals("unknown"))) {
				remoteAddr = request.getHeader("x-forwarded-for");
			}
		}
		if (remoteAddr == null) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		}
		if (remoteAddr == null) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		if (remoteAddr == null) {
			remoteAddr = request.getRemoteAddr();
		}
		return remoteAddr;
	}

	/**
	 * <b>方法名</b>：getRequest<br>
	 * <b>功能</b>：取得当前线程中的request<br>
	 * @author <font color='blue'>Administrator</font> 
	 * @date  2012-8-1 下午05:45:34
	 * @return
	 * @see org.apache.struts2.ServletActionContext#getRequest()
	 */
	public static HttpServletRequest getRequest() {
		if (ActionContext.getContext() == null) {
			return null;
		}
		return ServletActionContext.getRequest();
	}

	/**
	 * <b>方法名</b>：getParameter<br>
	 * <b>功能</b>：取得当前线程中的request中参数值。<br>
	 * @author <font color='blue'>Administrator</font> 
	 * @date  2012-9-17 下午03:23:49
	 * @param key
	 * @return
	 */
	public static String getParameter(String key) {
		if (ActionContext.getContext() == null) {
			return null;
		}
		return ServletActionContext.getRequest().getParameter(key);
	}
}
