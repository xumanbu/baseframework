package com.cxypub.baseframework.sdk.json;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.cxypub.baseframework.sdk.util.DateUtils;
import com.cxypub.baseframework.sdk.util.StringUtils;

/**
 * 
 * @ClassName: DateJsonValueProcessor
 * @Description: TODO
 * @author Comsys-徐飞
 * @date 2015年7月27日 下午1:41:09
 *
 */
public class DateJsonValueProcessor implements JsonValueProcessor {

	//默认时间格式
	public static String DEFAULT_PATTEN = "yyyy-MM-dd HH:mm:ss";

	private String patten;

	/**
	 * Constructor for DateJsonValueProcessor
	 * @param patten
	 */
	public DateJsonValueProcessor(String patten) {
		super();
		this.patten = patten;
	}

	/**
	 * 没有参数过滤构造函数，默认时间格式:yyyy-MM-dd HH:mm:ss
	 * @see DateJsonValueProcessor.DEFAULT_PATTEN
	 * @author zohan
	 * Constructor for DateJsonValueProcessor
	 */
	public DateJsonValueProcessor() {
		super();
	}

	/* (non-Javadoc)
	 * @see net.sf.json.processors.JsonValueProcessor#processArrayValue(java.lang.Object, net.sf.json.JsonConfig)
	 */
	@Override
	public Object processArrayValue(Object value, JsonConfig config) {
		if (value.getClass().isArray()) {
			Date dates[] = (Date[]) value;
			String dateArray[] = new String[dates.length];
			for (int i = 0; i < dates.length; i++) {
				dateArray[i] = formatDate(dates[i]);
			}
			return dateArray;
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang.String, java.lang.Object, net.sf.json.JsonConfig)
	 */
	@Override
	public Object processObjectValue(String str, Object value, JsonConfig config) {
		if (null == value || value.equals(null)) {
			return "";
		}
		return formatDate((Date) value);
	}

	/**
	 * 
	 * <b>方法名</b>：formatDate<br>
	 * <b>功能</b>：格式化时间<br>
	 * @author <font color='blue'>zohan</font> 
	 * @date  2014-5-13 下午02:46:23
	 * @param value
	 * @return
	 */
	private String formatDate(Date value) {
		String date;
		try {
			if (StringUtils.isNullOrEmpty(patten)) {
				date = DateUtils.FormatDate(value, patten);
			} else {
				date = DateUtils.FormatDate(value, DEFAULT_PATTEN);
			}
		} catch (Exception e) {
			date = DateUtils.FormatDate(value, DEFAULT_PATTEN);
		}

		return date;
	}

}
