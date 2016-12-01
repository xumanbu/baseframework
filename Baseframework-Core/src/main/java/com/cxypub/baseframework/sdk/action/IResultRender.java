package com.cxypub.baseframework.sdk.action;

import java.io.InputStream;

/**
 * 
 * @ClassName: ResultRender
 * @Description: TODO
 * @author Comsys-徐飞
 * @date 2015年7月27日 下午1:38:28
 *
 */
public interface IResultRender {

	/**
	 * <b>方法名</b>：render<br>
	 * <b>功能</b>：直接输出内容的简便函数. eg. render("text/plain", "hello",
	 *              "encoding:UTF-8"); render("text/plain", "hello",
	 *              "no-cache:false"); render("text/plain", "hello",
	 *              "encoding:UTF-8", "no-cache:false");<br>
	 * @author <font color='blue'>liucong</font> 
	 * @date  2013-2-20 下午05:19:00
	 * @param contentType 输出的文本格式 如："text/html"，“text/plain”，"text/xml"
	 * @param content 对外输出的内容
	 * @param headers 可变的header数组
	 */
	public void render(final String contentType, final String content, final String... headers);

	/**
	 * <b>方法名</b>：renderText<br>
	 * <b>功能</b>：直接输出文本.<br>
	 * @author <font color='blue'>liucong</font> 
	 * @date  2013-2-20 下午05:20:04
	 * @param text
	 * @param headers
	 */
	public void renderText(final String text, final String... headers);

	/**
	 * <b>方法名</b>：renderHtml<br>
	 * <b>功能</b>：直接输出HTML.<br>
	 * @author <font color='blue'>liucong</font> 
	 * @date  2013-2-20 下午05:20:30
	 * @param html
	 * @param headers
	 */
	public void renderHtml(final String html, final String... headers);

	/**
	 * <b>方法名</b>：renderXml<br>
	 * <b>功能</b>：直接输出XML.<br>
	 * @author <font color='blue'>liucong</font> 
	 * @date  2013-2-20 下午05:20:48
	 * @param xml
	 * @param headers
	 */
	public void renderXml(final String xml, final String... headers);

	/**
	 * <b>方法名</b>：renderJson<br>
	 * <b>功能</b>：直接输出JSON.<br>
	 * @author <font color='blue'>liucong</font> 
	 * @date  2013-2-20 下午05:22:03
	 * @param object
	 * @param headers
	 */
	public void renderJson(final Object object, final String... headers);

	/**
	 * <b>方法名</b>：renderStream<br>
	 * <b>功能</b>：直接输出流.<br>
	 * @author <font color='blue'>liucong</font> 
	 * @date  2013-2-20 下午05:22:26
	 * @param contentType
	 * @param inputStream
	 * @param headers
	 */
	public void renderStream(final String contentType, final InputStream inputStream, final String... headers);
}
