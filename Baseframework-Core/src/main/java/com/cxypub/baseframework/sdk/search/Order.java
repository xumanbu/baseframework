package com.cxypub.baseframework.sdk.search;

/**
 * <p> Title: 智能营区综合管控系统-Order.java </p>
 * <p> Description: Intelligent Camp Monitoring System </p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company: www.pingtech.com.cn </p>
 * @author xufei
 * @version 4.0
 * @date  2014-11-2 下午2:56:00
 */
public class Order implements Search {

	/**
	 * 排序的方式 true:asc(升序);false:desc（降序）
	 */
	private boolean asc = true;

	/**
	 * 排序的属性名
	 */
	private String prop;

	public Order() {
	}

	/**
	 * Order的构造器
	 * @param propertyName 排序的属性名
	 * @param ascending 是否升序
	 */
	protected Order(String prop, boolean asc) {
		this.prop = prop;
		this.asc = asc;
	}

	/**
	 * 升序的order
	 * 
	 * @param propertyName
	 * @return Order
	 */
	public static Order asc(String propertyName) {
		return new Order(propertyName, true);
	}

	/**
	 * 降序的order
	 * 
	 * @param propertyName
	 * @return Order
	 */
	public static Order desc(String propertyName) {
		return new Order(propertyName, false);
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	@Override
	public String toString() {
		return prop + ' ' + (asc ? "asc" : "desc");
	}

	@Override
	public String toSHql() {
		return " " + prop + " " + (asc ? "asc" : "desc") + " ";
	}

}
