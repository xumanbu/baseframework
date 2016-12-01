package com.cxypub.baseframework.sdk.container;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class ServletContextUtil {
	/**
	 * @Title: getServletContext
	 * @Description: 获取ServletContext对象
	 * @return  传人参数
	 * @author 徐飞
	 */
	public static ServletContext getServletContext() {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext.getServletContext();
		return servletContext;
	}
}
