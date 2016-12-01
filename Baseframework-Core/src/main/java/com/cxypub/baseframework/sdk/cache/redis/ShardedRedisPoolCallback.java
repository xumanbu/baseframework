package com.cxypub.baseframework.sdk.cache.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * @ClassName: RedisPoolCallback
 * @Description: redis操作回调接口，此接口主要为JedisTemple模板使用
 * @author 徐飞
 * @date 2016年1月26日 下午2:35:41
 *
 * @param <T>
 */
public interface ShardedRedisPoolCallback<T> {
	/**
	 * @Title: doInJedis
	 * @Description: 回调执行方法，需要重新此方法，一般推荐使用匿名内部类
	 * @param jedis
	 * @return
	 * @author 徐飞
	 */
	T doInJedis(ShardedJedis shardedJedis);
}
