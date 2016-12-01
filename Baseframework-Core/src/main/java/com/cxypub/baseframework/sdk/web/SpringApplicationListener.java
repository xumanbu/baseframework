package com.cxypub.baseframework.sdk.web;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * 
 * @ClassName: InitListener
 * @Description: 监听系统启动  初始化spring 工具类
 * @author Comsys-陈鹏
 * @date 2015-5-7 下午05:48:09
 *
 */
@Component("initListener")
public class SpringApplicationListener implements ServletContextAware, ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		System.out.println("------------------------------------");
	}

	/**
	 * <p>Title: setServletContext</p>
	 * <p>Description: </p>
	 * @param arg0
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */

	@Override
	public void setServletContext(ServletContext arg0) {

	}

}
