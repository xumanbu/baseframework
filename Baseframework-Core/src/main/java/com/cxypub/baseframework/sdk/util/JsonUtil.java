/**
 * @Title: JsonUtil.java
 * @Package com.oao.oao_coreservice.common.tools
 * @Description: TODO
 * Copyright: Copyright (c) 2015 
 * Company:上海追月信息科技有限公司
 * 
 * @author oaoCoder-徐飞
 * @date 2015年7月24日 下午3:04:39
 * @version V1.0
 */

package com.cxypub.baseframework.sdk.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * @ClassName: JsonUtil
 * @Description: Json 转换
 * @author Comsys-徐飞
 * @date 2015年7月24日 下午3:04:39
 *
 */

public abstract class JsonUtil {

	/**
	 * <b>方法名：</b>：toJson<br>
	 * <b>功能说明：</b>：获取json对象的方法<br>
	 * @author <font color='blue'>徐飞</font> 
	 * @date  2015年12月21日 下午3:23:10
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	/**
	 * 
	 * @Title: toArray
	 * @author:徐飞
	 * @Description: json字符串转json数组
	 * @param jsonString
	 * @param clazz
	 * @return 
	 * @throws
	 */
	public static <T> List<T> toArray(String jsonString, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		// 创建一个JsonParser
		JsonParser parser = new JsonParser();
		Gson gson = new Gson();
		// 通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
		JsonElement el = parser.parse(jsonString);
		// 把JsonElement对象转换成JsonArray
		JsonArray jsonArray = null;
		if (el.isJsonArray()) {
			jsonArray = el.getAsJsonArray();
		}
		Iterator<JsonElement> it = jsonArray.iterator();
		while (it.hasNext()) {
			JsonElement e = it.next();
			// JsonElement转换为JavaBean对象
			T t = gson.fromJson(e, clazz);
			list.add(t);
		}

		return list;
	}

	/**
	 * 
	 * @Title: toObject
	 * @author:徐飞
	 * @Description: json字符串转Object
	 * @param json
	 * @param clazz
	 * @return 
	 * @throws
	 */
	public static <T> T toObject(String json, Class<T> clazz) {
		return new Gson().fromJson(json, clazz);
	}

}
