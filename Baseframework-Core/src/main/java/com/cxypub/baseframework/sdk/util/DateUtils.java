package com.cxypub.baseframework.sdk.util;

/**
 * DateUtils.java
 * 
 * @date 2010-8-4
 * 
 * Copyright @2010 BeiJing Pingtech Co. Ltd.
 * 
 * All right reserved.
 */
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * @description 日期工具类
 * 
 * @author wangrj
 */
@SuppressWarnings("unused")
public class DateUtils implements JsonValueProcessor {
	/**
	 * 当前时间的毫秒数
	 */
	static long now = System.currentTimeMillis();

	/**
	 * 当前日期
	 */
	public static java.util.Date CurrTime = new java.util.Date(now);

	/**
	 * 日期
	 */
	private static Date date = new Date();

	private static final long ONE_DAY = 24 * 60 * 60 * 1000;

	private static final long ONE_HOUR = 60 * 60 * 1000;

	private static final long ONE_MIN = 60 * 1000;

	static public final String DATE_FMT_1 = "yyyy-MM-dd";

	// ORA标准时间格式

	private static final SimpleDateFormat ORA_DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMdd");

	// 带时分秒的ORA标准时间格式

	private static final SimpleDateFormat ORA_DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	// 手动设置日期格式默认
	private String format = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Constructor for DateUtils
	 */
	public DateUtils() {
	}

	/**
	 * Constructor for DateUtils
	 * @param format
	 */
	public DateUtils(String format) {
		this.format = format;
	}

	/**
	 * @description 将日期转化为字符串,字符串格式("YYYY-MM-DD")，小时、分、秒被忽略
	 * @date 2010-8-4
	 * @author wangrj
	 * @param date 日期
	 * @return 带格式的字符串
	 */
	public static String DateToString(Date date) {
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String strDateTime = formater.format(date);
		return strDateTime;
	}

	/**
	 * @description 将日期转化为字符串,字符串格式("YYYY年-mm月-dd日")，小时、分、秒被忽略
	 * @date 2010-8-4
	 * @author wangrj
	 * @param date  日期
	 * @return 带格式的字符串
	 */
	public static String DateToStringText(Date date) {
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("yyyy年MM月dd日");
		String strDateTime = formater.format(date);
		return strDateTime;
	}

	/**
	 * @description	将日期转化为字符串,字符串格式自定义
	 * @date 2010-8-4
	 * @author wangrj
	 * @param Date 日期
	 * @param pattern 日期格式
	 * @return String 类型
	 */
	public static String DateToString(Date date, String pattern) throws Exception {
		String strDateTime = null;
		try {
			java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(pattern);
			strDateTime = formater.format(date);
		} catch (Exception ex) {
			throw ex;
		}
		return strDateTime;
	}

	/**
	 * @description 将传入的年月日转化为Date类型
	 * @date 2010-8-4
	 * @author wangrj
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return Date类型
	 */
	public static Date YmdToDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		return calendar.getTime();
	}

	/**
	 * 
	 * @description 将日期转化为字符串
	 * @date 2010-8-4
	 * @author wangrj
	 * @param date 日期 
	 * @return 字符串格式("MM/dd HH:mm:ss")
	 */
	public static String communityDateToString(Date date) {
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("MM/dd HH:mm:ss");
		String strDateTime = formater.format(date);
		return strDateTime;
	}

	/**
	 * @description 得到某一天的所在周的第一天和最后一天（周一（） 周日()）和这一天所在的为这一年的第几周
	 * @date 2010-8-4
	 * @author wangrj
	 * @param str  日期格式的字符串
	 * @param pattern 日期格式
	 * @param type 类型：周日为一周开始 为 0，周一为一周的开始为1
	 * @return  数组：某一天的所在周的第一天和最后一天（周一（） 周日()）和这一天所在的为这一年的第几周
	 */
	public static String[] getWeekParams(String str, String pattern, int type) {
		String[] three = new String[3];
		if (pattern == null || pattern.equals("")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}

		SimpleDateFormat formater = null;
		try {
			formater = new SimpleDateFormat(pattern);
		} catch (Exception e) {
			formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		Date date = new Date();
		try {
			date = formater.parse(str);
		} catch (ParseException e) {
			System.out.println("Parse date Error in DateUtils");
		}
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(type + 1);
		c.setTime(date);
		c.get(Calendar.WEEK_OF_YEAR);
		c.set(Calendar.DAY_OF_WEEK, 2);
		three[0] = formater.format(c.getTime());
		c.set(Calendar.DAY_OF_WEEK, 8);
		three[1] = formater.format(c.getTime());
		three[2] = String.valueOf(c.get(Calendar.WEEK_OF_YEAR));
		return three;
	}

	/**
	 * <b>方法名</b>：getDateWeekDays<br>
	 * <b>功能</b>：从某天开始，计算第weekday的开始是哪一天<br>
	 * @author <font color='blue'>gugq</font> 
	 * @date  2013-2-6 上午10:58:17
	 * @param date 开始时间
	 * @param weekday 第几周
	 * @param type 周日为一周开始 为 0，周一为一天开始的为1
	 * @return 日期
	 * @throws Exception
	 */
	public static Date getDateWeekDays(Date date, int weekday, int type) throws Exception {
		String format = "yyyy-MM-dd HH:mm:ss";
		int weekdayIs = dateToWeekDay(date, type);
		String[] day = getWeekParams(DateToString(date, format), format, type);
		//
		if (type == 0) {
			if (weekday > weekdayIs) {
				date.setTime(date.getTime() + (weekday - weekdayIs) * 24 * 60 * 60 * 1000);
				return date;
			} else if (weekday == weekdayIs) {
				return date;
			} else if (weekday < weekdayIs) {
				date.setTime(date.getTime() + (7 - weekdayIs + weekday) * 24 * 60 * 60 * 1000);
				return date;
			}
		} else {
			if (weekday > weekdayIs) {
				date.setTime(date.getTime() + (weekday - weekdayIs) * 24 * 60 * 60 * 1000);
				System.out.println(dateToWeekDay(date, type));
				return date;
			} else if (weekday == weekdayIs) {
				System.out.println(dateToWeekDay(date, type));
				return date;
			} else if (weekday < weekdayIs) {
				date.setTime(date.getTime() + 7 * 24 * 60 * 60 * 1000);
				System.out.println(dateToWeekDay(date, type));
				return date;
			}
		}
		return date;
	}

	/**
	 * <b>方法名</b>：StringToDate<br>
	 * <b>功能</b>：将字符窜转到日期时间开<br>
	 * @author <font color='blue'>gugq</font> 
	 * @date  2013-2-6 上午11:02:44
	 * @param str 字符串
	 * @return 日期
	 */
	@SuppressWarnings("finally")
	public static Date StringToDate(String str) {
		Date dateTime = null;
		try {
			if (!(str == null || str.equals(""))) {
				java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
				dateTime = formater.parse(str);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		} finally {
			return dateTime;
		}
	}

	/**
	 * @description 日期时间带时分秒的Timestamp表示,
	 * @date 2010-8-4
	 * @author wangrj
	 * @param 日期字符串 必须符合 格式 例如2010-09-02 11:12:12.022111111
	 * @return Timestamp 时间戳
	 */
	public static Timestamp StringToDateHMS(String str) throws Exception {
		Timestamp time = null;
		try {
			time = java.sql.Timestamp.valueOf(str);
		} catch (Exception ex) {

			System.out.print(ex.toString());
		}

		return time;

	}

	/**
	 * @description 得一个date对象对应的日期的0点0分0秒时刻的Date对象。
	 * @date 2010-8-4
	 * @author wangrj
	 * @param date 一个日期
	 * @return Date对象。
	 */
	public static Date getMinDateOfDay(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));

		return calendar.getTime();
	}

	/**
	 * @description 取得一个date对象对应的日期的23点59分59秒时刻的Date对象。
	 * @date 2010-8-4
	 * @author wangrj	 
	 * @param date 一个日期
	 * @return Date对象。
	 */
	public static Date getMaxDateOfDay(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

		return calendar.getTime();
	}

	/**
	 * @description 字符串按照格式转化日期
	 * @date 2010-7-13
	 * @author liuhh
	 * @param dateString 日期字符串
	 * @param DataFormat 日期格式
	 * @return 转换后的日期 
	 */
	public static Date parseDate(String dateString, String DataFormat) {
		if (DataFormat == null) {
			DataFormat = "yyyy-MM-dd";
		}
		SimpleDateFormat fordate = new SimpleDateFormat(DataFormat);
		if (dateString == null || dateString.equals("")) {
			return null;
		}
		try {

			return fordate.parse(dateString);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * @description 字符串按照格式转化java.sql.Date 默认格式 "yyyy-MM-dd"
	 * @date 2011-2-16
	 * @author xuf
	 * @param dateString 日期字符串
	 * @return 转换后的日期 
	 */
	public static java.sql.Date parseSQLDate(String dateString) {
		SimpleDateFormat fordate = new SimpleDateFormat("yyyy-MM-dd");
		if (dateString == null || dateString.equals("")) {
			return null;
		}
		try {
			Date d = fordate.parse(dateString);
			java.sql.Date sd = new java.sql.Date(d.getTime());
			return sd;
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * @description 将两个格式为HH:MM:SS的时间字符串相加，例如：00:59:06 + 01:00:59 返回 02:00:05。
	 * @date 2010-7-12
	 * @author liuhh
	 * @param time1 要累计的时间字符串
	 * @param time2 要累计的时间字符串
	 * @return 累计后的时间字符串
	 */
	public static String addTwoTimeStr(String time1, String time2) {

		String returnStr = "00:00:00";
		if (time1 != null && !time1.equalsIgnoreCase("") && time2 != null && !time2.equalsIgnoreCase("")) {
			String[] time1Array = time1.split(":");
			String[] time2Array = time2.split(":");
			int hour1 = (new Integer(time1Array[0])).intValue();
			int hour2 = (new Integer(time2Array[0])).intValue();
			int min1 = (new Integer(time1Array[1])).intValue();
			int min2 = (new Integer(time2Array[1])).intValue();
			int sec1 = (new Integer(time1Array[2])).intValue();
			int sec2 = (new Integer(time2Array[2])).intValue();

			String lastSec, lastMin, lastHour;

			int totalSec = sec1 + sec2;
			if (totalSec / 60 > 0) {
				min1 = min1 + totalSec / 60;
			}
			if (totalSec % 60 > 9) {
				lastSec = new Integer(totalSec % 60).toString();
			} else {
				lastSec = new String("0" + new Integer(totalSec % 60).toString());
			}

			int totalMin = min1 + min2;
			if (totalMin / 60 > 0) {
				hour1 = hour1 + totalMin / 60;
			}
			if (totalMin % 60 > 9) {
				lastMin = new Integer(totalMin % 60).toString();
			} else {
				lastMin = new String("0" + new Integer(totalMin % 60).toString());
			}

			int totalHour = hour1 + hour2;
			if (totalHour % 24 > 9) {
				lastHour = new Integer(totalHour % 24).toString();
			} else {
				lastHour = new String("0" + new Integer(totalHour % 24).toString());
			}

			returnStr = lastHour + ":" + lastMin + ":" + lastSec;
		} else if (time1 != null && !time1.equalsIgnoreCase("")) {
			returnStr = time1.substring(0, 8);
		} else if (time2 != null && !time2.equalsIgnoreCase("")) {
			returnStr = time2.substring(0, 8);
		} else {
			returnStr = "00:00:00";
		}

		return returnStr;
	}

	/**
	 * @description 创建一个标准ORA时间格式的克隆
	 * @date 2010-7-12
	 * @author liuhh
	 * @return 标准ORA时间格式的克隆
	 */
	private static synchronized DateFormat getOraDateTimeFormat() {
		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_FORMAT.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}

	/**
	 * @description 创建一个带分秒的ORA时间格式的克隆
	 * @date 2010-7-12
	 * @author liuhh
	 * @return 标准ORA时间格式的克隆
	 */
	private static synchronized DateFormat getOraExtendDateTimeFormat() {
		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_EXTENDED_FORMAT.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}

	/**
	 * @description 得到系统当前的日期 格式为YYYY-MM-DD
	 * @date 2010-7-12
	 * @author liuhh
	 * @return 系统当前的日期 格式为YYYY-MM-DD
	 */
	public static String getSystemCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return doTransform(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * @description 返回格式为YYYY-MM-DD
	 * @date 2010-7-12
	 * @author liuhh
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return YYYY-MM-DD格式的字符串
	 */
	private static String doTransform(int year, int month, int day) {
		StringBuffer result = new StringBuffer();
		result.append(String.valueOf(year)).append("-").append(month < 10 ? "0" + String.valueOf(month) : String.valueOf(month)).append("-").append(day < 10 ? "0" + String.valueOf(day) : String.valueOf(day));
		return result.toString();
	}

	/**
	 * @description 获得昨天的日期
	 * @date 2010-7-12
	 * @author liuhh
	 * @return 指定日期的上一天 格式:YYYY-MM-DD
	 */
	public static synchronized String getDayBeforeToday() {
		Date date = new Date(System.currentTimeMillis());
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, -1);
		return doTransform(toString(gc.getTime(), getOraExtendDateTimeFormat())).substring(0, 10);
	}

	/** 
	 * @description 获得指定日期的上一天的日期
	 * @date 2010-7-12
	 * @author liuhh
	 * @param dateStr  指定的日期 格式:YYYY-MM-DD
	 * @return
	 */
	public static synchronized String getDayBeforeToday(String dateStr) {
		Date date = toDayStartDate(dateStr);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, -1);
		return doTransform(toString(gc.getTime(), getOraExtendDateTimeFormat())).substring(0, 10);
	}

	/**
	 * @description 获得明天的日期
	 * @date 2010-7-12
	 * @author liuhh
	 * @return 指定日期的下一天 格式:YYYY-MM-DD
	 */
	public static synchronized String getDayAfterToday() {
		Date date = new Date(System.currentTimeMillis());
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, 1);
		return doTransform(toString(gc.getTime(), getOraExtendDateTimeFormat())).substring(0, 10);
	}

	/**
	 * @description 获得指定日期的下一天的日期
	 * @date 2010-7-12
	 * @author liuhh
	 * @param dateStr 指定的日期 格式:YYYY-MM-DD
	 * @return 指定日期的下一天 格式:YYYY-MM-DD
	 */
	public static synchronized String getDayAfterToday(String dateStr) {
		Date date = toDayStartDate(dateStr);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, 1);
		return doTransform(toString(gc.getTime(), getOraExtendDateTimeFormat())).substring(0, 10);
	}

	/**
	 * @description 以当前日期为准，获得以后几个月的日期
	 * @date 2010-7-12
	 * @author liuhh
	 * @param months 月
	 * @return Date类型的日期
	 */
	public static synchronized Date getDayAfterMonth(int months) {
		Date date = new Date(System.currentTimeMillis());
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.MONTH, months);
		return gc.getTime();
	}

	/**
	 * @description 将输入格式为2004-8-13,2004-10-8类型的字符串转换为标准的Date类型,这种Date类型 对应的日期格式为YYYY-MM-DD
	 * 				00:00:00,代表一天的开始时刻
	 * @date 2010-7-12
	 * @author liuhh
	 * @param dateStr 要转换的字符串
	 * @return 转换后的Date对象
	 */
	public static synchronized Date toDayStartDate(String dateStr) {
		String[] list = dateStr.split("-");
		int year = Integer.parseInt(list[0]);
		int month = Integer.parseInt(list[1]);
		int day = Integer.parseInt(list[2]);
		Calendar cale = Calendar.getInstance();
		cale.set(year, month - 1, day, 0, 0, 0);
		return cale.getTime();

	}

	/**
	 * @description 将两个scorm时间相加
	 * @date 2010-7-12
	 * @author liuhh
	 * @param scormTime1 scorm时间,格式为00:00:00(1..2).0(1..3)
	 * @param scormTime2 scorm时间,格式为00:00:00(1..2).0(1..3)
	 * @return 两个scorm时间相加的结果
	 */
	public static synchronized String addTwoScormTime(String scormTime1, String scormTime2) {
		int dotIndex1 = scormTime1.indexOf(".");
		int hh1 = Integer.parseInt(scormTime1.substring(0, 2));
		int mm1 = Integer.parseInt(scormTime1.substring(3, 5));
		int ss1 = 0;
		if (dotIndex1 != -1) {
			ss1 = Integer.parseInt(scormTime1.substring(6, dotIndex1));
		} else {
			ss1 = Integer.parseInt(scormTime1.substring(6, scormTime1.length()));
		}
		int ms1 = 0;
		if (dotIndex1 != -1) {
			ms1 = Integer.parseInt(scormTime1.substring(dotIndex1 + 1, scormTime1.length()));
		}

		int dotIndex2 = scormTime2.indexOf(".");
		int hh2 = Integer.parseInt(scormTime2.substring(0, 2));
		int mm2 = Integer.parseInt(scormTime2.substring(3, 5));
		int ss2 = 0;
		if (dotIndex2 != -1) {
			ss2 = Integer.parseInt(scormTime2.substring(6, dotIndex2));
		} else {
			ss2 = Integer.parseInt(scormTime2.substring(6, scormTime2.length()));
		}
		int ms2 = 0;
		if (dotIndex2 != -1) {
			ms2 = Integer.parseInt(scormTime2.substring(dotIndex2 + 1, scormTime2.length()));
		}

		int hh = 0;
		int mm = 0;
		int ss = 0;
		int ms = 0;

		if (ms1 + ms2 >= 1000) {
			ss = 1;
			ms = ms1 + ms2 - 1000;
		} else {
			ms = ms1 + ms2;
		}
		if (ss1 + ss2 + ss >= 60) {
			mm = 1;
			ss = ss1 + ss2 + ss - 60;
		} else {
			ss = ss1 + ss2 + ss;
		}
		if (mm1 + mm2 + mm >= 60) {
			hh = 1;
			mm = mm1 + mm2 + mm - 60;
		} else {
			mm = mm1 + mm2 + mm;
		}
		hh = hh + hh1 + hh2;

		StringBuffer sb = new StringBuffer();
		if (hh < 10) {
			sb.append("0").append(hh);
		} else {
			sb.append(hh);
		}
		sb.append(":");
		if (mm < 10) {
			sb.append("0").append(mm);
		} else {
			sb.append(mm);
		}
		sb.append(":");
		if (ss < 10) {
			sb.append("0").append(ss);
		} else {
			sb.append(ss);
		}
		sb.append(".");
		if (ms < 10) {
			sb.append(ms).append("00");
		} else if (ms < 100) {
			sb.append(ms).append("0");
		} else {
			sb.append(ms);
		}
		return sb.toString();
	}

	/**
	 * @description 根据timeType返回当前日期与传入日期的差值（当前日期减传入日期） 当要求返回月份的时候，date的日期必须和当前的日期相等，
	 * 				否则返回0（例如：2003-2-23 和 2004-6-12由于23号和12号不是同一天，固返回0， 2003-2-23 和 2005-6-23
	 * 				则需计算相差的月份，包括年，此例应返回28（个月）。 2003-2-23 和 2001-6-23
	 * 				也需计算相差的月份，包括年，此例应返回-20（个月））
	 * @date 2010-7-12
	 * @author liuhh
	 * @param date 要与当前日期比较的日期
	 * @param timeType 0代表返回两个日期相差月数，1代表返回两个日期相差天数
	 * @return 根据timeType返回当前日期与传入日期的差值
	 */
	public static int CompareDateWithNow(Date date, int timeType) {
		Date now = Calendar.getInstance().getTime();

		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTime(now);
		calendarNow.set(Calendar.HOUR_OF_DAY, 0);
		calendarNow.set(Calendar.MINUTE, 0);
		calendarNow.set(Calendar.SECOND, 0);
		calendarNow.set(Calendar.MILLISECOND, 0);

		Calendar calendarPara = Calendar.getInstance();
		calendarPara.setTime(date);
		calendarPara.set(Calendar.HOUR_OF_DAY, 0);
		calendarPara.set(Calendar.MINUTE, 0);
		calendarPara.set(Calendar.SECOND, 0);
		calendarPara.set(Calendar.MILLISECOND, 0);

		float nowTime = now.getTime();
		float dateTime = date.getTime();

		if (timeType == 0) {
			if (calendarNow.get(Calendar.DAY_OF_YEAR) == calendarPara.get(Calendar.DAY_OF_YEAR)) {
				return 0;
			}
			return (calendarNow.get(Calendar.YEAR) - calendarPara.get(Calendar.YEAR)) * 12 + calendarNow.get(Calendar.MONTH) - calendarPara.get(Calendar.MONTH);
		} else {
			int result = 0;
			if (calendarNow.compareTo(calendarPara) == 0) {
				return result;
			} else if (calendarNow.compareTo(calendarPara) > 0) {
				while (calendarNow.compareTo(calendarPara) >= 0) {
					calendarNow.add(Calendar.DAY_OF_YEAR, -1);
					result++;
				}
				return result - 1;
			} else if (calendarNow.compareTo(calendarPara) < 0) {
				while (calendarNow.compareTo(calendarPara) <= 0) {
					calendarNow.add(Calendar.DAY_OF_YEAR, 1);
					result--;
				}
				return result + 1;
			}
			return 0;
			// float result = nowTime - dateTime;
			// float day = 24 * 60 * 60 * 1000;
			// // System.out.println("day "+day);
			// // result = (result > 0) ? result : -result;
			// // System.out.println(result);
			// result = result / day;
			// Float resultFloat = new Float(result);
			// float fraction = result - resultFloat.intValue();
			// if (fraction > 0.5) {
			// return resultFloat.intValue() + 1;
			// }
			// else if (fraction < -0.5) {
			// return resultFloat.intValue() - 1;
			// }
			// else {
			// return resultFloat.intValue();
			// }
		}
	}

	/**
	 * @description 将一个日期对象转换成为指定日期、时间格式的字符串。 如果日期对象为空，返回一个空字符串对象.
	 * @date 2010-7-12
	 * @author liuhh
	 * @param theDate  要转换的日期对象
	 * @param theDateFormat 返回的日期字符串的格式
	 * @return 转换为制定格式的日期
	 */
	public static synchronized String toString(Date theDate, DateFormat theDateFormat) {
		if (theDate == null) {
			return "";
		} else {
			return theDateFormat.format(theDate);
		}
	}

	/**
	 * @description 返回格式为YYYY-MM-DD hh:mm:ss
	 * @date 2010-7-12
	 * @author liuhh
	 * @param date  输入格式为ORA标准时间格式
	 * @return  格式为YYYY-MM-DD hh:mm:ss 的时间串
	 */
	private static String doTransform(String date) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(date.substring(0, 4));
		buffer.append("-");
		buffer.append(date.substring(4, 6));
		buffer.append("-");
		buffer.append(date.substring(6, 8));
		buffer.append(" ");
		buffer.append(date.substring(8, 10));
		buffer.append(":");
		buffer.append(date.substring(10, 12));
		buffer.append(":");
		buffer.append(date.substring(12, 14));

		return buffer.toString();
	}

	/**
	 * @description 获得日期是一个星期的第几天
	 * @date 2011-1-26
	 * @author xf
	 * @param date 转换的日期
	 * @param type 周日为一周开始 为 0，周一为一天开始的为1
	 * @return  日期是一个星期的第几天
	 */
	public static int dateToWeekDay(Date date, int type) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (type == 0) {
			return calendar.get(Calendar.DAY_OF_WEEK);
		} else if (type == 1) {
			if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
				return 7;
			} else {
				return calendar.get(Calendar.DAY_OF_WEEK) - 1;
			}
		} else {
			return -1;
		}
	}

	/**
	 * @description 获得当前星期开始日期
	 * @date 2010-7-12
	 * @author liuhh
	 * @return 当前星期开始日期
	 */
	public static Date getWeekStartDate() {
		Calendar calendar = Calendar.getInstance();
		Date firstDateOfWeek;
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DAY_OF_WEEK, -(dayOfWeek - 1));
		firstDateOfWeek = calendar.getTime();
		calendar.add(Calendar.DAY_OF_WEEK, 6);
		return firstDateOfWeek;
	}

	/**
	 * @description 获得当前星期结束日期
	 * @date 2010-7-12
	 * @author liuhh
	 * @return 当前星期结束日期
	 */
	public static Date getWeekEndDate() {
		Calendar calendar = Calendar.getInstance();
		Date lastDateOfWeek;
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DAY_OF_WEEK, -(dayOfWeek - 1));
		calendar.add(Calendar.DAY_OF_WEEK, 6);
		lastDateOfWeek = calendar.getTime();
		return lastDateOfWeek;
	}

	/**
	 * @description 获得当前月份的第一天
	 * @date 2010-7-12
	 * @author liuhh
	 * @return 当前月份的第一天
	 */
	public static Date getMonthStartDate() {
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(new Date(System.currentTimeMillis()));
		gc.set(Calendar.DAY_OF_MONTH, 1);
		return toDayStartDate(df.format(gc.getTime()));
	}

	/**
	 * @description 获得当前月份的最后一天
	 * @date 2010-7-12
	 * @author liuhh
	 * @return Date 前月份的最后一天
	 */
	public static Date getMonthEndDate() {
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.text.SimpleDateFormat dff = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		return toDayEndDate(df.format(cal.getTime()));
	}

	/**
	 * @description 将输入格式为2004-8-13,2004-10-8类型的字符串转换为标准的Date类型,这种Date类型 对应的日期格式为YYYY-MM-DD
	 * 				23:59:59,代表一天的结束时刻
	 * @date 2010-7-12
	 * @author liuhh
	 * @param dateStr 输入格式:2004-8-13,2004-10-8
	 * @return 转换后的Date对象 ，格式为YYYY-MM-DD 23:59:59
	 */
	public static synchronized Date toDayEndDate(String dateStr) {
		String[] list = dateStr.split("-");
		int year = new Integer(list[0]).intValue();
		int month = new Integer(list[1]).intValue();
		int day = new Integer(list[2]).intValue();
		Calendar cale = Calendar.getInstance();
		cale.set(year, month - 1, day, 23, 59, 59);
		return cale.getTime();

	}

	/**
	 * @description 得到几天前的日期
	 * @date 2010-12-30
	 * @author wangrj
	 * @param d 标准日期
	 * @param day 第几天
	 * @return 几天前的日期
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * @description 得到几年前的日期
	 * @date 2010-12-30
	 * @author wangrj
	 * @param d  标准日期
	 * @param day 第几年
	 * @return 几年前的日期
	 */
	public static Date getYearBefore(Date d, int year) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.YEAR, now.get(Calendar.YEAR) - year);
		return now.getTime();
	}

	/**
	 * @description 得到几月前的日期
	 * @date 2010-12-30
	 * @author wangrj
	 * @param d  标准日期
	 * @param day  第几月
	 * @return  几月前的日期
	 */
	public static Date getMonthBefore(Date d, int month) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.MONTH, now.get(Calendar.MONTH) - month);
		return now.getTime();
	}

	/**
	 * @description 将毫秒字符串转换为Date的方法
	 * @date 2011-3-22
	 * @author xuf
	 * @param str 毫秒字符串
	 * @return 转换后的Date
	 */
	public static Date parseDateByMilliSecondString(String str) {
		Long millisecond = Long.parseLong(str);
		return new Date(millisecond);
	}

	/**
	 * 
	 * @description 得到指定日期的七天后的日期
	 * @date 2011-6-16
	 * @author xuxa
	 * @param date 日期
	 * @return 日期
	 */
	public static Date getDateAfterSevenDays(Date date) {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sdfStrDate = null;
		Date sevenDate = null;
		try {
			sdfStrDate = DateToString(date, "yyyy-MM-dd");
			Date dateOne = parseStringToSpecialDates(sdfStrDate);
			sevenDate = new Date(dateOne.getTime() + (6 * 24 * 3600 * 1000));

		} catch (Exception e) {
			e.printStackTrace();

		}
		return sevenDate;
	}

	/**
	 * 
	 * @description得到指定日期的第二天
	 * @date 2011-6-16
	 * @author xuxa
	 * @param date
	 * @return
	 */
	public static Date getDateAfterSpecialDate(Date date) {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sdfStrDate = null;
		Date tomorrowDate = null;
		try {
			sdfStrDate = DateToString(date, "yyyy-MM-dd");
			Date dateOne = parseStringToSpecialDates(sdfStrDate);
			tomorrowDate = new Date(dateOne.getTime() + (1 * 24 * 3600 * 1000));
			System.out.println(tomorrowDate);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return tomorrowDate;
	}

	public static Date parseStringToSpecialDates(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOne = null;
		try {
			dateOne = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateOne;
	}

	public static long getNow() {
		CurrTime = new java.util.Date();
		return CurrTime.getTime();
	}

	/**
	 * 通过字符串HH:mm:ss得到毫秒值

	 *
	 * @param s
	 *            String 格式为HH:mm:ss的字符串
	 * @return long 转换后的毫秒值

	 */
	public static long getTimebyString(String s) {
		int i = s.indexOf(":");
		int j = s.lastIndexOf(":");
		long time = 0;
		if ((i != -1) && (j != -1)) {
			int hh = Integer.parseInt(s.substring(0, i));
			int mm = Integer.parseInt(s.substring(i + 1, j));
			int ss = Integer.parseInt(s.substring(j + 1, s.length()));
			time = hh * 60 * 60 * 1000 + mm * 60 * 1000 + ss * 1000;
		}
		return time;
	}

	/**
	 * 通过字符串HH:mm得到毫秒值

	 *
	 * @param s
	 *            String 格式为HH:mm的字符串
	 * @return long 转换后的毫秒值

	 */
	public static long getTimesbyString(String s) {
		int i = s.indexOf(":");
		long time = 0;
		if ((i != -1)) {
			int hh = Integer.parseInt(s.substring(0, i));
			int mm = Integer.parseInt(s.substring(i + 1, s.length()));
			time = hh * 60 * 60 * 1000 + mm * 60 * 1000;
		}
		return time;
	}

	/**
	 *String 格式为HH:mm的字符串
	 * 单位毫秒
	 * 根据2个时间计算时间差
	 */
	public static long getTimeDifference(String begintime, String endtime) {
		long begintimelong = getTimesbyString(begintime);
		long endtimelong = getTimesbyString(endtime);
		if (begintimelong >= endtimelong) {
			return 0;
		} else {
			return endtimelong - begintimelong;
		}
	}

	/**
	 * 格式化日期

	 *
	 * @param date
	 *            Date 日期
	 * @param string
	 *            String 格式字符串

	 * @return String 格式化后日期
	 */
	public static String FormatDate(Date date, String string) {
		SimpleDateFormat dateformat = new SimpleDateFormat(string);
		return dateformat.format(date);
	}

	/**
	 * 取得当前时间格式yyyy-MM-dd HH:mm:ss
	 *
	 * @return String
	 */
	public static String getCurrTime() {
		java.util.Date date_time = new Date();
		return FormatDate(date_time, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getYYMMDD() {
		return FormatDate(new java.util.Date(), "yyMMdd");
	}

	/**
	 * 取得当前时间格式yyyy-MM-dd HH:mm:ss:millis
	 *
	 * @return String
	 */
	public static String getCurrMillisTime() {
		long l = System.currentTimeMillis();
		String d = DateUtils.getCurrTime();
		return d + ":" + (l - DateUtils.getTimeInMillis(d, "yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * 取得当前时间格式yyyy-MM-dd HH:mm
	 *
	 * @return String
	 */
	public static String getCurrShortTime() {
		Date date = new Date();
		return FormatDate(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 取得当前时间格式yyyy-MM-dd
	 *
	 * @return String
	 */
	public static String getCurrDate() {
		CurrTime = new java.util.Date();
		return FormatDate(CurrTime, "yyyy-MM-dd");
	}

	public static String getYYMMDDHHMMSS() {
		return FormatDate(new java.util.Date(), "yyMMddHHmmss");
	}

	/**
	 * 取得当前年份
	 *
	 * @return String
	 */
	public static String getCurrYear() {
		CurrTime = new java.util.Date();
		return FormatDate(CurrTime, "yyyy");

	}

	/**
	 * 取得当前月份
	 *
	 * @return String
	 */
	public static String getCurrMonth() {
		CurrTime = new java.util.Date();
		return FormatDate(CurrTime, "MM");

	}

	/**
	 * 取得当前日

	 *
	 * @return String
	 */
	public static String getCurrDay() {
		CurrTime = new java.util.Date();
		return FormatDate(CurrTime, "dd");

	}

	/**
	 * 取得当前小时
	 *
	 * @return String
	 */
	public static String getCurrHours() {
		CurrTime = new java.util.Date();
		return FormatDate(CurrTime, "HH");
	}

	/**
	 * 取得当前分钟
	 *
	 * @return String
	 */
	public static String getCurrMinutes() {
		CurrTime = new java.util.Date();
		return FormatDate(CurrTime, "mm");
	}

	/**
	 * 取得当前秒

	 *
	 * @return String
	 */
	public static String getCurrSeconds() {
		CurrTime = new java.util.Date();
		return FormatDate(CurrTime, "ss");
	}

	/**
	 * 转换日期格式yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 *            Date 日期
	 * @return String
	 */
	public static String dayToString(Date date) {
		return FormatDate(date, "yyyy-MM-dd");
	}

	/**
	 * 转换日期格式yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 *            Date 日期
	 * @return String
	 */
	public static String dateToString(Date date) {
		return FormatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 转换日期格式yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 *            Date 日期
	 * @return String
	 */
	public static String dateToString(java.sql.Date date) {
		return FormatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 转换日期格式yyyy-MM-dd HH
	 *
	 * @param date
	 *            Date 日期
	 * @return String
	 */
	public static String dateToString1(java.sql.Date date) {
		return FormatDate(date, "yyyy-MM-dd");
	}

	/**
	 * 转换日期格式yyyy-MM-dd HH:mm
	 *
	 * @param date
	 *            Date 日期
	 * @return String
	 */
	public static String dateToShortString(Date date) {
		return FormatDate(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 说明：由时间格式的字符串获得年数
	 *
	 * @param dateString
	 *            时间格式字符串

	 * @return
	 */
	public static int getYearByString(String dateString) {
		int year = 0;
		int i = dateString.indexOf("-");
		int j = dateString.lastIndexOf("-");
		if (i != -1 && j != -1) {
			year = Integer.parseInt(dateString.substring(0, i));
		}
		return year;
	}

	/**
	 * 说明：由时间格式的字符串获得月份数

	 *
	 * @param dateString
	 *            时间格式字符串

	 * @return
	 */
	public static int getMonthByString(String dateString) {
		int month = 1;
		int i = dateString.indexOf("-");
		int j = dateString.lastIndexOf("-");
		if (i != -1 && j != -1) {
			month = Integer.parseInt(dateString.substring(i + 1, j));
		}
		return month;
	}

	/**
	 * 说明: 字符串转换为日期 (默认格式 yyyy-MM-dd)
	 *
	 * @param dateString
	 *            日期格式字符串

	 * @return 转换后的日期
	 */
	public static Date stringToDate(String dateString) {
		String sf = "yyyy-MM-dd";
		Date dt = stringToDate(dateString, sf);
		return dt;
	}

	/**
	 * 说明：字符串转换为时间（默认格式 yyyy-MM-dd HH:mm:ss)
	 *
	 * @param dateString
	 *            日期格式字符串

	 * @return 转换后的日期
	 */
	public static Date StringToTime(String dateString) {
		String sf = "yyyy-MM-dd HH:mm:ss";
		Date dt = stringToDate(dateString, sf);
		return dt;
	}

	/**
	 * 说明：时间字符串格式转换（默认格式 yyyyMMddHHmmss)，主要为接口部分所用

	 *
	 * @param dateString
	 *            日期格式字符串 （默认格式 yyyyMMddHHmmss)
	 * @return 转换后的日期格式字符串（yyyy-MM-dd HH:mm:ss） add by zhangjie
	 */
	public static String timeStringChange(String dateString) {
		String sf = "yyyyMMddHHmmss";
		Date dt = stringToDate(dateString, sf);
		String sc = dateToString(dt);
		return sc;
	}

	/**
	 * 获得当前日期的年月，形成格式为YYMM的字符串
	 *
	 * @return String 年月字符串，格式YYMM
	 */
	public static String getPublicDate() {
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH) + 1;
		String pd = ("" + c.get(Calendar.YEAR)).substring(2, 4) + (month > 9 ? "" + month : "0" + month);
		return pd;
	}

	/**
	 * 根据参数计算并给出当前日期返回制定月所在的年

	 *
	 * @param getback
	 *            int 返回制定月所在的年

	 * @return int
	 */
	public static int backyear(int getback) {
		int back_year = 0;
		CurrTime = new java.util.Date();
		int nowyear = Integer.parseInt(FormatDate(CurrTime, "yyyy"));
		int nowmonth = Integer.parseInt(FormatDate(CurrTime, "MM"));
		if (nowmonth > getback) {
			back_year = nowyear;
		} else {
			back_year = nowyear - 1;
		}
		return back_year;
	}

	/**
	 * 根据参数计算并给出当前日期返回制定月所在的年

	 *
	 * @param getback
	 *            int 返回制定月所在的年

	 * @return int
	 */
	public static int backmonth(int getback) {
		int back_month = 0;
		CurrTime = new java.util.Date();
		int nowyear = Integer.parseInt(FormatDate(CurrTime, "yyyy"));
		int nowmonth = Integer.parseInt(FormatDate(CurrTime, "MM"));
		if (nowmonth > getback) {
			back_month = nowmonth - getback;
		} else {
			back_month = nowmonth - getback + 12;
		}
		return back_month;
	}

	/**
	 * 根据参数计算并给出所需的年
	 *
	 * @param date
	 *            Date 日期
	 * @param getback
	 *            int 返回制定月所在的年

	 * @return String
	 */
	public static String dateToyear(Date date, int getback) {
		String dateyear = FormatDate(date, "yyyy");
		String datemonth = FormatDate(date, "MM");
		String newyear = "";
		int oldyear = Integer.parseInt(dateyear);
		int oldmonth = Integer.parseInt(datemonth);
		if (oldmonth > getback) {
			newyear = String.valueOf(oldyear);
		} else {
			newyear = String.valueOf(oldyear - 1);
		}
		return newyear;
	}

	/**
	 * 根据参数计算并给出所需的月
	 *
	 * @param date
	 *            Date 日期
	 * @param getback
	 *            int 返回制定月所在的年

	 * @return String
	 */
	public static String dateTomonth(Date date, int getback) {
		String dateyear = FormatDate(date, "yyyy");
		String datemonth = FormatDate(date, "MM");
		String newmonth = "";
		int oldmonth = Integer.parseInt(datemonth);
		if (oldmonth > getback) {
			newmonth = String.valueOf(oldmonth - getback);
		} else {
			newmonth = String.valueOf(oldmonth + 12 - getback);
		}
		return newmonth;
	}

	/**
	 * 根据给定的两个时间计算时间差
	 *
	 * @param date_1
	 *            Date 日期
	 * @param date_2
	 *            Date 日期
	 * @return int
	 */
	public static int subtime(Date date_1, Date date_2) {
		String dateyear_1 = FormatDate(date_1, "yyyy");
		String datemonth_1 = FormatDate(date_1, "MM");
		String dateyear_2 = FormatDate(date_2, "yyyy");
		String datemonth_2 = FormatDate(date_2, "MM");
		int year_1 = Integer.parseInt(dateyear_1);
		int year_2 = Integer.parseInt(dateyear_2);
		int month_1 = Integer.parseInt(datemonth_1);
		int month_2 = Integer.parseInt(datemonth_2);
		int subtime = (year_2 - year_1) * 12 + (month_2 - month_1) + 1;
		return subtime;
	}

	/**
	 * 根据给定的两个时间字符串计算时间差

	 *
	 * @param dateString1
	 *            String 时间
	 * @param dateString2
	 *            String 时间
	 * @return long 时间差以秒为单位(dateString2-dateString1)
	 */
	public static long subsecond(String dateString1, String dateString2) {
		Date date_1 = StringToTime(dateString1);
		Date date_2 = StringToTime(dateString2);
		long subsecond = (date_2.getTime() - date_1.getTime()) / 1000;
		return subsecond;
	}

	/**
	 * 将一个表示秒数的字符串转换为n小时n分钟n秒的字符串

	 *
	 * @param dateString
	 *            String 日期字符串

	 * @return String
	 */
	public static String changeFormat(String dateString) {
		int date = Integer.parseInt(dateString);
		int hh = 0;
		int mm = 0;
		int ss = 0;
		hh = date / 3600;
		mm = date % 3600 / 60;
		ss = date % 3600 % 60;
		String changedtime = hh + "小时";
		if (mm != 0) {
			changedtime += (hh + "分钟");
		}
		if (ss != 0) {
			if (mm == 0) {
				changedtime += "0分钟";
			}
			changedtime += (ss + "秒");
		}
		return changedtime;
	}

	/**
	 * 将一个表示秒数的字符串转换为天数
	 *
	 * @param timelength
	 *            long 秒数
	 * @return long
	 */
	public static long changeSecondToDay(long timelength) {
		long day = 0;
		if (timelength % (24 * 3600) == 0) {
			day = timelength / 24 / 3600;
		} else {
			day = timelength / 24 / 3600 + 1;
		}
		return day;
	}

	/**
	 * 将一个表示秒数的字符串转换为分钟
	 *
	 * @param timelengthString
	 *            String 秒数的字符串
	 * @return String
	 */
	public static String changeSecondToMinute(String timelengthString) {
		long minute = 0;
		String minuteString = "";
		long timelength = Long.parseLong(timelengthString);
		if (timelength % 60 == 0) {
			minute = timelength / 60;
		} else {
			minute = timelength / 60 + 1;
		}
		minuteString = Long.toString(minute) + "分";
		return minuteString;
	}

	/**
	 * <b>方法名</b>：changeDayToMinute<br>
	 * <b>功能</b>：改变日到分<br>
	 * @author <font color='blue'>gugq</font> 
	 * @date  2013-2-6 上午11:15:08
	 * @param dayString 日字符串
	 * @return
	 */
	public static String changeDayToMinute(String dayString) {
		long minute = 0;
		long day = Long.parseLong(dayString);
		String minuteString = "";
		minute = day * 24 * 60;
		minuteString = Long.toString(minute) + "分";
		return minuteString;
	}

	/**
	 * <b>方法名</b>：changeMinuteToHour<br>
	 * <b>功能</b>：改变分到小时<br>
	 * @author <font color='blue'>gugq</font> 
	 * @date  2013-2-6 上午11:14:00
	 * @param minuteString 分字符串
	 * @return 字符
	 */
	public static String changeMinuteToHour(String minuteString) {
		long minute = Long.parseLong(minuteString);
		long hour = 0;
		String hourString = "";
		if (minute % 60 == 0) {
			hour = minute / 60;
			hourString = Long.toString(hour) + "小时";
		} else {
			hour = minute;
			hourString = Long.toString(hour) + "分钟";
		}
		return hourString;
	}

	/**
	 * <b>方法名</b>：stringToDate<br>
	 * <b>功能</b>：日期格式字符串<br>
	 * @author <font color='blue'>gugq</font> 
	 * @date  2013-2-6 上午11:13:12
	 * @param dateString 日期格式字符串
	 * @param sf 日期格式化定义
	 * @return 日期
	 */
	public static Date stringToDate(String dateString, String sf) {
		ParsePosition pos = new ParsePosition(0);
		SimpleDateFormat sdf = new SimpleDateFormat(sf);
		Date dt = sdf.parse(dateString, pos);
		return dt;
	}

	/**
	 * <b>方法名</b>：getMonthStr<br>
	 * <b>功能</b>：取得当前月份字符串<br>
	 * @author <font color='blue'>gugq</font> 
	 * @date  2013-2-6 上午11:12:44
	 * @return 字符串
	 */
	@SuppressWarnings("deprecation")
	public static String getMonthStr() {
		java.util.Date date = new java.util.Date();
		return (date.getYear() + 1900) + "" + ("" + date.getMonth());
	}

	/**
	 * <b>方法名</b>：changeOracleDate<br>
	 * <b>功能</b>： 将从oracle中取出的Date类型字符串（YYYY－MM－DD HH24：MI：SS.ms）转化为YYYY-MM-DD HH24:MI:SS<br>
	 * @author <font color='blue'>gugq</font> 
	 * @date  2013-2-6 上午11:11:49 
	 * @param date 日期
	 * @return 日期
	 */
	public static String changeOracleDate(String date) {
		if (date.indexOf(".") != -1) {
			return date.substring(0, date.indexOf("."));
		} else {
			return date;
		}
	}

	/**
	 * <b>方法名</b>：getBeginTimeEqualsEndTimeFlag<br>
	 * <b>功能</b>：当前时间 挂起开始时间 结束时间 标志 06-06-09 11；00 06-06-09 12；00<br>
	 * 				Modify by ppLiang 当前时间 挂起开始时间 结束时间 标志 06-06-09 11；00 06-06-09 12；00
	 * 				06-06-09 13；00 0 挂起开始时间 > 当前时间 06-06-09 12；00 06-06-09 11；00 06-06-09
	 * 				13；00 1 挂起开始时间 < 当前时间 06-06-09 13；00 06-06-09 11；00 06-06-09 12；00 2
	 * 				挂起结束时间 < 当前时间
	 * @author <font color='blue'>gugq</font> 
	 * @date  2013-2-6 上午11:10:39
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static String getBeginTimeEqualsEndTimeFlag(String beginTime, String endTime) {
		// 1.如果“挂起开始时间”<“当前时间“，并且小于“挂起结束时间”，挂起标志位为"1"
		// 2.如果“挂起结束时间”大于“当前时间”，挂起标志位为"2"
		// 3.如果“挂起开始时间”大于“当前时间”，挂起标志位为"0"

		Date currentDate = new Date();
		Date hangBeginTime = stringToDate(beginTime);
		Date hangEndTime = stringToDate(endTime);
		String flag = "";

		if (hangBeginTime.before(hangEndTime)) { // 挂起开始时间 < 挂起结束时间
			if ((hangBeginTime.before(currentDate)) && (currentDate.before(hangEndTime))) {
				// 挂起开始时间 < 当前时间 && 挂起开始时间 < 挂起结束时间
				flag = "1";
			} else if (hangEndTime.before(currentDate)) { // 挂起结束时间 < 当前时间
				flag = "2";
			} else if (currentDate.before(hangBeginTime)) { // 挂起开始时间 > 当前时间
				flag = "0";
			}
		}
		return flag;
	}

	public static String getCurrTimes() {
		java.util.Date date_time = new Date();
		return FormatDate(date_time, "HH:mm:ss");
	}

	/**
	 * <b>方法名</b>：GetWeekDay<br>
	 * <b>功能</b>：根据传近来的时间和时间格式得到对应的星期<br>
	 * @author <font color='blue'>gugq</font> 
	 * @date  2013-2-6 上午11:09:53
	 * @param TempDate  时间
	 * @param format "yyyy-MM-dd"
	 * @return 1~7代表星期日到星期六
	 */
	public static int GetWeekDay(String TempDate, String format) {
		int temp = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(TempDate));
			temp = c.get(Calendar.DAY_OF_WEEK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * 根据 传入的时间、时间格式 和 天数，返回指定的日期
	 *time 当前时间
	 *format 时间格式 yyyy-MM-dd HH:mm:ss
	 *date 天数，如果是后3天则 -3，前3天则 是 3
	 */
	public static String getTimeToAddDate(String time, String format, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getDefault());
		calendar.clear();
		calendar.setTime(stringToDate(time, format));// 开始时间

		calendar.add(Calendar.DAY_OF_MONTH, date);

		return FormatDate(calendar.getTime(), format);
	}

	/**
	 * <b>方法名</b>：getTimeInMillisToAdd<br>
	 * <b>功能</b>：根据 传入的时间、时间格式 和 天数，返回指定时间段后多少毫秒会自动追加<br>
	 * @author <font color='blue'>gugq</font> 
	 * @date  2013-2-6 上午11:08:43
	 * @param time 当前时间
	 * @param format 时间格式 yyyy-MM-dd HH:mm:ss
	 * @param timeInMillis 毫秒，表示当前时间段后多少毫秒会自动追加
	 * @return 计算结果 Long型
	 */
	public static String getTimeInMillisToAdd(String time, String format, long timeInMillis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getDefault());
		calendar.clear();
		calendar.setTime(stringToDate(time, format));// 开始时间

		calendar.setTimeInMillis(calendar.getTimeInMillis() + timeInMillis);
		return dateToString(calendar.getTime());
	}

	/**
	 * <b>方法名</b>：getTimeInMillis<br>
	 * <b>功能</b>：根据 传入的时间、时间格式 ，返回毫秒<br>
	 * @author <font color='blue'>gugq</font> 
	 * @date  2013-2-6 上午11:07:58
	 * @param time 指定时间
	 * @param format 时间格式 yyyy-MM-dd HH:mm:ss
	 * @return 得到时间Long型
	 */
	public static long getTimeInMillis(String time, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getDefault());
		calendar.clear();
		calendar.setTime(stringToDate(time, format));// 开始时间

		return calendar.getTimeInMillis();
	}

	/**
	 * 传一个时间段得到这个时间段中有多少个周末
	 *
	 * @param beginDay
	 *            int 2007-01-01
	 * @param endDay
	 *            int 2007-01-31
	 * @return int 1－31的一天

	 */
	@SuppressWarnings("static-access")
	public static int getWeekDays(String beginDay, String endDay) {
		int k = 0;
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTimeZone(TimeZone.getDefault());
		calBegin.clear();
		calBegin.setTime(stringToDate(beginDay));// 开始时间

		Calendar calEnd = Calendar.getInstance();
		calEnd.setTimeZone(TimeZone.getDefault());
		calEnd.clear();
		calEnd.setTime(stringToDate(endDay));// 结束时间

		while (calBegin.before(calEnd)) {
			// 星期天 是 1 ，星期6 为 7
			if (calBegin.get(calBegin.DAY_OF_WEEK) == 1 || calBegin.get(calBegin.DAY_OF_WEEK) == 7) {
				++k; // 有就给K加1
			}
			calBegin.roll(calBegin.DATE, true); // 当前日期向前动1天递增
			// System.out.println(calBegin.getTime());
			if (calBegin.after(calEnd)) { // 开始时间大于结束时间 就结束循环

				break;
			}
		}
		if (GetWeekDay(endDay, "yyyy-MM-dd") == 1 || GetWeekDay(endDay, "yyyy-MM-dd") == 7) {
			k += 1;
		}
		return k;
	}

	/**
	 * <b>方法名</b>：getDayOfWeek<br>
	 * <b>功能</b>：计算星期几的函数<br>
	 * @author <font color='blue'>gugq</font> 
	 * @date  2013-2-6 上午11:07:01
	 * @param year 年
	 * @param month 月份
	 * @param date 日期
	 * @return 是周几
	 */
	public static int getDayOfWeek(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getDefault());
		cal.clear();
		cal.set(year, month - 1, date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * <b>方法名</b>：getLastDayOfMonth<br>
	 * <b>功能</b>：根据传进来的参数得到当前月最后一天<br>
	 * @author <font color='blue'>gugq</font> 
	 * @date  2013-2-6 上午11:04:42
	 * @param time 日期时间
	 * @param format 日期格式
	 * @return 哪一天（整形）
	 */
	public static int getLastDayOfMonth(String time, String format) {
		Calendar cDay1 = Calendar.getInstance();
		cDay1.setTime(stringToDate(time, format));
		int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
		return lastDay;
	}

	/**
	 * <b>方法名</b>：getTimeOfInform<br>
	 * <b>功能</b>：版本投产通知,获得投产时间<br>
	 * @author <font color='blue'>gugq</font> 
	 * @date  2013-2-6 上午11:05:46
	 * @return
	 */
	public static String getTimeOfInform() {
		String time = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(CurrTime);
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		if ((week - 7) == 0) {
			if ((day + 4) - lastDay > 0) {
				month += 1;
				day = (day + 4) - lastDay;
			} else {
				day = (day + 4);
			}
		} else if ((week - 4) >= 0) {
			if ((day + 7 - week) - lastDay > 0) {
				month += 1;
				day = (day + 7 - week) - lastDay;
			} else {
				day = (day + 7 - week);
			}
		} else {
			if ((day + 4 - week) - lastDay > 0) {
				month += 1;
				day = (day + 4 - week) - lastDay;
			} else {
				day = (day + 4 - week);
			}
		}
		String m = null;
		String d = null;
		if (month <= 9) {
			m = "0" + month;
		} else {
			m = (new Integer(month)).toString();
		}
		if (day <= 9) {
			d = "0" + day;
		} else {
			d = (new Integer(day)).toString();
		}
		time = year + "-" + m + "-" + d + " 22:00:00";
		// SimpleDateFormat dateformat = new
		// SimpleDateFormat("yyyy-MM-dd-EEE",Locale.ENGLISH);
		// time=dateformat.format(CurrTime);
		return time;
	}

	/**
	 * <b>方法名</b>：getMonthEqualsDays<br>
	 * <b>功能</b>：开始时间  多少个月之后，计算从开始时间到 几个月之后，中间存在多少天<br>
	 * @author <font color='blue'>gugq</font> 
	 * @date  2013-2-6 上午11:06:04
	 * @param begintime 开始时间
	 * @param month 月份
	 * @return 天数
	 */
	public static int getMonthEqualsDays(String begintime, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getDefault());
		calendar.clear();
		calendar.setTime(stringToDate(begintime));// 开始时间
		calendar.add(Calendar.MONTH, month);

		return (int) ((calendar.getTimeInMillis() - stringToDate(begintime).getTime()) / 1000 / 24 / 60 / 60);
	}

	/* (non-Javadoc)
	 * @see net.sf.json.processors.JsonValueProcessor#processArrayValue(java.lang.Object, net.sf.json.JsonConfig)
	 */
	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		String[] obj = {};
		if (value instanceof Date[]) {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			Date[] dates = (Date[]) value;
			obj = new String[dates.length];
			for (int i = 0; i < dates.length; i++) {
				obj[i] = sf.format(dates[i]);
			}
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang.String, java.lang.Object, net.sf.json.JsonConfig)
	 */
	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		if (value instanceof Date) {
			String str = new SimpleDateFormat(format).format((Date) value);
			return str;
		}
		return value == null ? null : value.toString();
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	public static void main(String[] args) {
		String calDate = "2007-02-03";
		Date date1 = DateUtils.parseDate("2012-07-21 00:00:00", "yyyy-MM-dd HH:mm:ss");
		System.out.println(DateUtils.CompareDateWithNow(date1, 1));
		// System.out.println(getWeekDays("2007-02-03","2007-02-25"));
		// System.out.println(getLastDayOfMonth("2008-2" ,"yyyy-MM"));
		// System.out.println(GetWeekDay("2007-04-29" ,"yyyy-MM-dd"));
		// System.out.println(CurrTime);
		// System.out.println(getTimeOfInform());
		// System.out.println(getTimeToAddDate("2007-08-31","yyyy-MM-dd",1));
		// System.out.println(getTimeInMillisToAdd(getCurrTime(),"yyyy-MM-dd HH:mm:ss",-2*60*1000));
		// System.out.println(getTimeInMillisToAdd("2007-08-31 13:00:00","yyyy-MM-dd HH:mm:ss",60*60*1000));
		// System.out.println(dateStr.substring(0,dateStr.indexOf("--")));
		// System.out.println(dateStr.substring(dateStr.indexOf("--")+2));
		// System.out.println(getWeekDays("2007-09-08","2007-09-23"));
		// System.out.println(1000*60*60*24);
		// System.out.println(DateUtils.stringToDate(DateUtils.getCurrDate()));
		// System.out.println(DateUtils.stringToDate("2007-9-23"));
		// System.out.println(DateUtils.stringToDate(dateStr).before(DateUtils.stringToDate(DateUtils.getCurrDate())));
		// System.out.println(timeStringChange(dateStr));
		// System.out.println(getPublicDate());
		// System.out.println(calDate.substring(0,calDate.indexOf("-"))+"--"+calDate.substring(calDate.indexOf("-")+1,calDate.lastIndexOf("-"))+"--"+calDate.substring(calDate.lastIndexOf("-")+1));
	}
}
