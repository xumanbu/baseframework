package com.cxypub.baseframework.sdk.log;

/**
 * @ClassName: LogDefault
 * @Description: TODO
 * @author Comsys-徐飞
 * @date 2015年7月27日 下午1:41:43
 *
 */
public interface LogDefault {

	/**
	 * <b>方法名</b>：debug<br>
	 * <b>功能</b>：debug信息接口<br>
	 * @param message
	 */
	public void debug(Object message);

	/**
	 * <b>方法名</b>：debug<br>
	 * <b>功能</b>：debug信息接口<br>
	 * @param message
	 * @param args
	 */
	public void debug(String message, Object... args);

	/**
	 * <b>方法名</b>：info<br>
	 * <b>功能</b>：info信息接口<br>
	 * @param message
	 */
	public void info(Object message);

	/**
	 * <b>方法名</b>：info<br>
	 * <b>功能</b>：info信息接口<br>
	 * @param message
	 * @param args
	 */
	public void info(String message, Object... args);

	/**
	 * <b>方法名</b>：warn<br>
	 * <b>功能</b>：warn信息接口<br>
	 * @param message
	 */
	public void warn(Object message);

	/**
	 * <b>方法名</b>：warn<br>
	 * <b>功能</b>：warn信息接口<br>
	 * @param message
	 * @param args
	 */
	public void warn(String message, Object... args);

	/**
	 * <b>方法名</b>：error<br>
	 * <b>功能</b>：erro信息接口<br>
	 * @param message
	 */
	public void error(Object message);

	/**
	 * <b>方法名</b>：error<br>
	 * <b>功能</b>：erro信息接口<br>
	 * @param e
	 * @param message
	 * @param args
	 */
	public void error(Throwable e, String message, Object... args);

}
