package com.cxypub.baseframework.sdk.cache.redis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import com.cxypub.baseframework.sdk.dictionary.entity.Dictionary;

public class RedisTest {
	public static void main(String[] args) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(500);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000 * 100);
		config.setTestOnBorrow(true);
		// JedisPool jedisPool = new JedisPool(config, "192.168.1.213", 6379);
		List<JedisShardInfo> jedisShardInfoList = new ArrayList<JedisShardInfo>();
		jedisShardInfoList.add(new JedisShardInfo("192.168.1.213", 6379, "ma1"));
		jedisShardInfoList.add(new JedisShardInfo("192.168.1.215", 6379, "ma2"));
		ShardedJedisPool shareJedisPool = new ShardedJedisPool(config, jedisShardInfoList);
		JedisTemple jedisTemple = new JedisTemple(shareJedisPool);
		ShardedRedisCacheClient client = new ShardedRedisCacheClient(jedisTemple);
		Dictionary dict = new Dictionary();
		dict.setId("qwertryruyrtutyu");
		dict.setDictChineseName("上海");
		dict.setCreateTime(new Date());
		client.add("test003", dict);
		Dictionary dict2 = (Dictionary) client.get("test003");
		System.out.println(dict2);
		System.out.println(dict == dict2);

		for (int i = 0; i < 5; i++) {
			// client.add("tt" + i, 2222222);
			// client.add("gg" + i, 2222222);
			System.out.println(client.get("gg" + i));
		}
	}
}
