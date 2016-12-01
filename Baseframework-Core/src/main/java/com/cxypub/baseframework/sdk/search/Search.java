package com.cxypub.baseframework.sdk.search;

/**
 * <p> Title: 智能营区综合管控系统-Search.java </p>
 * <p> Description: Intelligent Camp Monitoring System </p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company: www.pingtech.com.cn </p>
 * @author xufei
 * @version 4.0
 * @date  2014-11-2 下午2:54:14
 */
public interface Search {
	/**
	 * @功能说明：将查询条件转换为sql或者hql供查询数据库
	 * @author xufei
	 * @date  2014-11-2 下午2:54:54
	 * @return
	 */
	String toSHql();
}
