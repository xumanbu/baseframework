package com.cxypub.baseframework.sdk.container;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * <b>类名</b>：SpringContextUtil.java<br>
 *
 * <p><b>标题</b>：品恩产品研发  </p>
 * <p><b>描述</b>：
 *           获取spring容器相关信息
 * </p>
 * <p><b>版权声明</b>：Copyright (c) 2012</p>
 * <p><b>公司</b>：品恩科技 </p>
 * @author <font color='blue'>liucong</font> 
 * @version 1.0
 * @date  2012-9-27 上午10:17:03
 */
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	 */

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getContext() {
		checkApplicationContext();
		return applicationContext;
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		return (T) applicationContext.getBeansOfType(clazz);
	}

	private static void checkApplicationContext() {
		Assert.notNull(applicationContext, "applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtil");
	}
}
