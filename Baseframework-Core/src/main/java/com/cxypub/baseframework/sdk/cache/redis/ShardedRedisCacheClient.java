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
package com.cxypub.baseframework.sdk.cache.redis;

import redis.clients.jedis.ShardedJedis;
import redis.clients.util.SafeEncoder;

import com.cxypub.baseframework.sdk.cache.SimpleCache;
import com.cxypub.baseframework.sdk.util.ObjectUtils;

/**
 * @ClassName: RedisCacheClient
 * @Description: redis缓存客户端
 * @author 徐飞
 * @date 2015-4-16 上午10:42:32
 *
 */
public class ShardedRedisCacheClient implements SimpleCache {

	private JedisTemple jedisTemple;

	public ShardedRedisCacheClient(JedisTemple jedisTemple) {
		this.jedisTemple = jedisTemple;
	}

	@Override
	public boolean add(final String key, final Object valueObject) {
		try {
			jedisTemple.execute(new ShardedRedisPoolCallback<Boolean>() {
				@Override
				public Boolean doInJedis(ShardedJedis jedis) {
					jedis.set(SafeEncoder.encode(key), ObjectUtils.object2Byte(valueObject));
					return true;
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Object get(final String key) {

		return jedisTemple.execute(new ShardedRedisPoolCallback<Object>() {
			@Override
			public Object doInJedis(ShardedJedis jedis) {
				byte[] cacheValue = jedis.get(SafeEncoder.encode(key));
				if (cacheValue != null) {
					return ObjectUtils.byte2Object(cacheValue);
				}
				return null;
			}

		});
	}

	@Override
	public long delete(final String key) {
		return jedisTemple.execute(new ShardedRedisPoolCallback<Long>() {
			@Override
			public Long doInJedis(ShardedJedis jedis) {
				return jedis.del(key);
			}
		});
	}

	@Override
	public boolean add(final String key, Object value, final int seconds) {
		try {
			this.add(key, value);
			jedisTemple.execute(new ShardedRedisPoolCallback<Long>() {
				@Override
				public Long doInJedis(ShardedJedis jedis) {
					return jedis.expire(key, seconds);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean exists(final String key) {
		return jedisTemple.execute(new ShardedRedisPoolCallback<Boolean>() {
			@Override
			public Boolean doInJedis(ShardedJedis jedis) {
				return jedis.exists(key);
			}
		});
	}

}
