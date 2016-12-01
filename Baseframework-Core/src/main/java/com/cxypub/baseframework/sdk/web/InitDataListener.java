package com.cxypub.baseframework.sdk.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class InitDataListener implements ServletContextListener {

	protected final Logger log = Logger.getLogger(this.getClass());

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext sc = sce.getServletContext();
		String contextPath = sc.getContextPath();
		if (contextPath.equals("/")) {
			contextPath = "";
		}
		sc.setAttribute("ctx", contextPath);
		log.info("初始化项目名称:ctx=" + contextPath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
