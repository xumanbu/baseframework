package com.cxypub.baseframework.sdk.cache.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @ClassName: JedisTemple
 * @Description: Jedis 操作模板类，为啥要这个？请参照{@link JdbcTemple} 封装重复不必要的操作
 * @author 徐飞
 * @date 2016年1月26日 下午2:37:24
 *
 */
public class JedisTemple {

	/**
	 * 非切片连接池
	 */
	private JedisPool jedisPool;

	/**
	 * 切片连接池，有多个缓存服务器使用
	 */
	private ShardedJedisPool shardedJedisPool;

	public JedisTemple(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public JedisTemple(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	/**
	 * @Title: execute
	 * @Description: 执行{@link RedisPoolCallback#doInJedis(Jedis)}的方法
	 * @param action
	 * @return
	 * @author 徐飞
	 */
	public <T> T execute(RedisPoolCallback<T> action) {
		T value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return action.doInJedis(jedis);
		} catch (Exception e) {
			// 释放redis对象
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(jedisPool, jedis);
		}

		return value;
	}

	/**
	 * @Title: execute
	 * @Description: 执行{@link RedisPoolCallback#doInJedis(Jedis)}的方法
	 * @param action
	 * @return
	 * @author 徐飞
	 */
	public <T> T execute(ShardedRedisPoolCallback<T> action) {
		T value = null;
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return action.doInJedis(jedis);
		} catch (Exception e) {
			// 释放redis对象
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(shardedJedisPool, jedis);
		}

		return value;
	}

	/** 
	* 返还到连接池 
	* @param pool  
	* @param redis 
	*/
	private void returnResource(ShardedJedisPool pool, ShardedJedis redis) {
		// 如果redis为空不返回
		if (redis != null) {
			pool.returnResource(redis);
		}
	}

	/** 
	 * 返还到连接池 
	 * @param pool  
	 * @param redis 
	 */
	private void returnResource(JedisPool pool, Jedis redis) {
		// 如果redis为空不返回
		if (redis != null) {
			pool.returnResource(redis);
		}
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

}
