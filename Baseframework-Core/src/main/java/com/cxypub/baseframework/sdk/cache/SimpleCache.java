/**
 * @Title: DispersedCachClient.java
 * @Package oao_framwork.oao_common.tools.cache
 * @Description: TODO
 * Copyright: Copyright (c) 2015 
 * Company:上海追月信息科技有限公司
 * 
 * @author oaoCoder-束文奇
 * @date 2015-4-14 上午11:51:18
 * @version V1.0
 */
package com.cxypub.baseframework.sdk.cache;

/**
 * @ClassName: DistributedCacheClient
 * @Description: 缓存接口
 * @author 徐飞
 * @date 2016年1月26日 上午11:41:27
 *
 */
public interface SimpleCache {

	/**
	 * @Title: add
	 * @Description: 添加一个缓冲数据
	 * @param key 字符串的缓存key
	 * @param value 缓冲的缓存数据
	 * @return
	 * @author 徐飞
	 */
	boolean add(String key, Object value);

	/**
	 * @Title: add
	 * @Description: 缓存一个数据，并指定缓存过期时间
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 * @author 徐飞
	 */
	boolean add(String key, Object value, int seconds);

	/**
	 * @Title: get
	 * @Description: 根据key获取到一直值
	 * @param key 字符串的缓存key
	 * @return
	 * @author 徐飞
	 */
	Object get(String key);

	/**
	 * @Title: delete
	 * @Description: 删除一个数据问题
	 * @param key 字符串的缓存key
	 * @return
	 * @author 徐飞
	 */
	long delete(String key);

	/**
	 * @Title: exists
	 * @Description: 判断指定key是否在缓存中已经存在
	 * @param key 字符串的缓存key
	 * @return
	 * @author 徐飞
	 */
	boolean exists(String key);

}
