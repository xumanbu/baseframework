/**
 * @Title: DispersedCachClientImp.java
 * @Package oao_framwork.oao_common.tools.cacheclient.imp
 * @Description: TODO
 * Copyright: Copyright (c) 2015 
 * Company:上海追月信息科技有限公司
 * 
 * @author oaoCoder-束文奇
 * @date 2015-4-16 上午10:42:32
 * @version V1.0
 */
package com.cxypub.baseframework.sdk.cache.memcached;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.cxypub.baseframework.sdk.cache.SimpleCache;
import com.danga.MemCached.MemCachedClient;

/**
 * @ClassName: MemcacheCacheClient
 * @Description: memcached缓存实现
 * @author 徐飞
 * @date 2016年1月26日 上午11:51:55
 *
 */
public class MemcacheCacheClient implements SimpleCache {

	/** 缓存客户端 **/
	@Autowired
	private MemCachedClient memCachedClient;

	@Override
	public boolean add(String key, Object value) {
		return memCachedClient.set(key, value);
	}

	@Override
	public boolean add(String key, Object value, int seconds) {
		return memCachedClient.set(key, value, new Date(seconds));
	}

	@Override
	public Object get(String key) {
		return memCachedClient.get(key);
	}

	@Override
	public long delete(String key) {
		return memCachedClient.delete(key) ? 1L : 0L;
	}

	@Override
	public boolean exists(String key) {
		return memCachedClient.keyExists(key);
	}

}
