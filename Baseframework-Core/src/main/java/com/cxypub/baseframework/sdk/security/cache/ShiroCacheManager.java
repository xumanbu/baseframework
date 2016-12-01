package com.cxypub.baseframework.sdk.security.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MapCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cxypub.baseframework.sdk.cache.SimpleCache;

/**
 * @ClassName: ShiroCacheManager
 * @Description: shiro的缓存管理，默认shiro缓存管理可以不用配置，这里使用了系统默认的分布式缓存在存储
 * 这个缓存主要是缓存登录认证信息，授权信息
 * @author Comsys-徐飞
 * @date 2015年12月29日 下午1:18:48
 *
 */
public class ShiroCacheManager implements CacheManager {

	@Autowired
	@Qualifier("memcacheClient")
	private SimpleCache simpleCache;

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		Object shiroCache = simpleCache.get(name);
		Map<K, V> shiroCacheMap = null;
		if (shiroCache != null && shiroCache instanceof Map) {
			shiroCacheMap = (Map<K, V>) shiroCache;
		} else {
			if (shiroCache == null) {
				shiroCacheMap = new ConcurrentHashMap<K, V>();
				simpleCache.add(name, shiroCacheMap);
			} else {
				throw new RuntimeException("存在与shiro缓存同样key的缓存，请检查项目，否则后果很严重！key" + name);
			}
		}
		return new MapCache<K, V>(name, shiroCacheMap);
	}
}
