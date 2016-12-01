package com.cxypub.baseframework.sdk.json;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * <b>类名</b>：DefaultJsonConfig.java<br>
 * <p><b>标题</b>：品恩产品研发  </p>
 * <p><b>描述</b>：
 *           自定义的JsonConfig转换器。
 * </p>
 * <p><b>版权声明</b>：Copyright (c) 2014</p>
 * <p><b>公司</b>：品恩科技 </p>
 * @author <font color='blue'>liucong</font> 
 * @version 1.0
 * @date  2014-5-13 下午02:17:24
 */
public class DefaultJsonConfig extends JsonConfig {

	public DefaultJsonConfig() {
		super.setIgnoreJPATransient(true);
		super.setIgnoreTransientFields(true);
		super.setCycleDetectionStrategy(CycleDetectionStrategy.NOPROP);
		super.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
		super.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor());
		super.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		super.registerJsonValueProcessor(java.sql.Time.class, new DateJsonValueProcessor("hh:mm:ss"));
		super.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-mm-dd hh:mm:ss"));
	}
}
