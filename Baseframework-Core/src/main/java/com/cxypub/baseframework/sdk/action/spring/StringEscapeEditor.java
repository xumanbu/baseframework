package com.cxypub.baseframework.sdk.action.spring;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

/**
 * 
 * @ClassName: StringEscapeEditor
 * @Description: 字符串转义
 * @author Comsys-陈鹏
 * @date 2015-5-13 下午05:01:23
 *
 */
public class StringEscapeEditor extends PropertyEditorSupport {
	/**
	 *  html 转义
	 */
	private boolean escapeHTML;

	/**
	 *  JavaScript 转义
	 */
	// private boolean escapeJavaScript;

	/**
	 * SQL 转义
	 */
	// private boolean escapeSQL;

	public StringEscapeEditor() {
		super();
	}

	public StringEscapeEditor(boolean escapeHTML) {
		super();
		this.escapeHTML = escapeHTML;
		// this.escapeJavaScript = escapeJavaScript;
		// this.escapeSQL = escapeSQL;
	}

	@Override
	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		} else {
			String value = text;
			// 会出现 字符转义之后 长度变化
			if (escapeHTML) {
				// value = StringEscapeUtils.escapeHtml(value);
				value = value.replaceAll("&", "&amp;").replaceAll("'", "&apos;").replaceAll("\"", "&quot;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			}
			// if (escapeJavaScript) {
			// //value = StringEscapeUtils.escapeJavaScript(value);
			// value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			// }
			// if (escapeSQL) {
			// value = StringEscapeUtils.escapeSql(value);
			// }
			// 去除空白
			value = StringUtils.deleteAny(value, " ");

			setValue(value);
		}
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return value != null ? value.toString() : "";
	}
}