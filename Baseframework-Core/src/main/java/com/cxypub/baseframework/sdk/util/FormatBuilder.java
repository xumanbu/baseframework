package com.cxypub.baseframework.sdk.util;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Map;

/**
 * <p> Title: 智能营区综合管控系统-DateFormatFactory.java </p>
 * <p> Description: Intelligent Camp Monitoring System </p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company: www.pingtech.com.cn </p>
 * @author xufei
 * @version 4.0
 * @date  2014-7-16 下午4:14:10
 */
public abstract class FormatBuilder {

	private static final Map<String, SimpleDateFormat> formatMap = new Hashtable<String, SimpleDateFormat>();

	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

	public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";

	public static final String yyyy_MM_dd = "yyyy-MM-dd";

	public static final String HH_mm_ss = "HH:mm:ss";

	public static final String HH_mm = "HH:mm";

	/**
	 * @功能说明：获得格式化时间的方法
	 * eg.SimpleDateFormat format = FormatBuilder.getFormat(FormatBuilder.yyyy_MM_dd);
	 * @author xufei
	 * @date  2014-7-17 下午1:35:21
	 * @param pattern
	 * @return
	 */
	public static SimpleDateFormat getFormat(String pattern) {
		SimpleDateFormat format = formatMap.get(pattern);
		if (format == null) {
			format = new SimpleDateFormat(pattern);
			formatMap.put(pattern, format);
		}
		return format;
	}

}
