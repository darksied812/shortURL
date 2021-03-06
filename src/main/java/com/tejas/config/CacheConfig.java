package com.tejas.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
/*
 * Config for Redis used for Caching
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
		
		@Bean
		public JedisConnectionFactory redisConnectionFactory() {
	    
			JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
			redisConnectionFactory.setHostName("127.0.0.1");
			redisConnectionFactory.setPort(6379);
			return redisConnectionFactory;
		}

		@Bean
		public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory cf) {
			RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
			redisTemplate.setConnectionFactory(cf);
			return redisTemplate;
		}

		@Bean
		public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
			RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
			cacheManager.setDefaultExpiration(300);
			return cacheManager;
		}
}
