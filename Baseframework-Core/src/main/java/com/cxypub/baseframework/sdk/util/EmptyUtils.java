package com.cxypub.baseframework.sdk.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 判断对象是否为空的工具类
 * <b>类名：</b>EmptyUtil.java<br>
 * <p><b>标题：</b>OAO时代商城</p>
 * <p><b>描述：</b>OAO时代商城系统 </p>
 * <p><b>版权声明：</b>Copyright (c) 2015</p>
 * <p><b>公司：</b>上海追月信息科技有限公司 </p>
 * @author <font color='blue'>徐飞</font> 
 * @version 2.0.1
 * @date  2015-5-12 上午9:26:33
 */
public abstract class EmptyUtils {

	/**
	 * 
	 * @方法名：对于String类型的非空判断
	 * @功能说明：对于String类型的非空判断
	 * @author 徐飞
	 * @date  Mar 28, 2013 9:26:09 AM
	 * @param str
	 * @return boolean true-为空，false-不为空
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null || "".equals(str.trim()) || "null".equalsIgnoreCase(str.trim()) || "undefined".equalsIgnoreCase(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @方法名：对于StringBuffer类型的非空判断
	 * @功能说明：对于StringBuffer类型的非空判断
	 * @author 徐飞
	 * @date  Mar 28, 2013 9:26:09 AM
	 * @param str
	 * @return boolean true-为空，false-不为空
	 */
	public static boolean isNullOrEmpty(StringBuffer str) {
		return (str == null || str.length() == 0);
	}

	/**
	 * 
	 * @方法名：对于string数组类型的非空判断
	 * @功能说明：对于string数组类型的非空判断
	 * @author 徐飞
	 * @date  Mar 28, 2013 9:26:09 AM
	 * @param str
	 * @return boolean true-为空，false-不为空
	 */
	public static boolean isNullOrEmpty(String[] str) {
		if (str == null || str.length == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @方法名：对于Long类型的非空判断
	 * @功能说明：对于Long类型的非空判断;true-为空或为0，false-不为空或大于0
	 * @author 徐飞
	 * @date  May 11, 2013 9:49:38 AM
	 * @param longTime Long类型的时间
	 * @return boolean true-为空或为0，false-不为空或大于0
	 */
	public static boolean isNullOrEmpty(Long longTime) {
		if (longTime == null || longTime <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @方法名：对于Object类型的非空判断
	 * @功能说明：对于Object类型的非空判断
	 * @author 徐飞
	 * @date  Mar 28, 2013 9:26:09 AM
	 * @param obj
	 * @return boolean true-为空，false-不为空
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null || "".equals(obj)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @方法名：对于Object数组类型的非空判断
	 * @功能说明：对于Object数组类型的非空判断
	 * @author 徐飞
	 * @date  Mar 28, 2013 9:26:09 AM
	 * @param obj
	 * @return boolean true-为空，false-不为空
	 */
	public static boolean isNullOrEmpty(Object[] obj) {
		if (obj == null || obj.length == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @方法名：对于Collection类型的非空判断
	 * @功能说明：对于Collection类型的非空判断
	 * @author 徐飞
	 * @date  Mar 28, 2013 9:26:09 AM
	 * @param str
	 * @return boolean true-为空，false-不为空
	 */
	public static boolean isNullOrEmpty(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @方法名：对于Map类型的非空判断
	 * @功能说明：对于Map类型的非空判断
	 * @author 徐飞
	 *@param map
	 *@return  传人参数
	 * @throws
	 */
	public static boolean isNullOrEmpty(Map<?, ?> map) {
		if (map == null || map.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @方法名：removeNullUnit
	 * @功能说明： 删除集合中的空元素
	 * @author 徐飞
	 * @date  2014-6-16 上午8:50:14
	 * @param xllxList
	 * @return
	 */
	public static <T> List<T> removeNullUnit(List<T> xllxList) {
		List<T> need = new ArrayList<T>();
		for (int i = 0; i < xllxList.size(); i++) {
			if (!isNullOrEmpty(xllxList.get(i))) {
				need.add(xllxList.get(i));
			}
		}
		return need;
	}

}
