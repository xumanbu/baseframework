package com.cxypub.baseframework.sdk.dao.hibernate.listener;

import java.util.List;

/**
 * <p> Title: 智能营区综合管控系统-DAOAfterExecuteListener.java </p>
 * <p> Description: Intelligent Camp Monitoring System </p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company: www.pingtech.com.cn </p>
 * @author xufei
 * @version 4.0
 * @date  2014-10-24 下午5:30:48
 */
public interface DAOAfterExecuteListener {
	/**
	 * <b>方法名</b>：beforeExecute<br>
	 * <b>功能</b>：查询执行后，对查询结果的处理。<br>
	 * @author <font color='blue'>liucong</font> 
	 * @date  2013-1-4 下午03:10:35
	 * @param sql
	 * @return
	 */
	public List<Object> findAfterExecute(List<Object> result);
}
