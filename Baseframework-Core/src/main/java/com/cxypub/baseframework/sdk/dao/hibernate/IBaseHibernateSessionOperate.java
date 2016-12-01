package com.cxypub.baseframework.sdk.dao.hibernate;

/**
 * <p> Title: 智能营区综合管控系统-IBaseHibernateSessionOperate.java </p>
 * <p> Description: Intelligent Camp Monitoring System </p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company: www.pingtech.com.cn </p>
 * @author xufei
 * @version 4.0
 * @date  2014-10-29 上午11:02:05
 */
public interface IBaseHibernateSessionOperate {
	/**
	 * <b>方法名</b>：flush<br>
	 * <b>功能</b>：Flush all pending saves, updates and deletes to the database.<br>
	 */
	public void flush();

	/**
	 * <b>方法名</b>：evict<br>
	 * <b>功能</b>：Remove the given object from the cache.<br>
	 */
	public void evict(Object entity);

	/**
	 * <b>方法名</b>：clear<br>
	 * <b>功能</b>：Remove all objects from the org.hibernate.Session cache, and cancel all pending saves, updates and deletes.<br>
	 */
	public void clear();
}
