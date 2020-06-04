package com.example.demo.config;

import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class RedisConfig {

  private Duration timeToLive = Duration.ofSeconds(500);//设置5秒过期

  @Bean
  public RedisCacheConfiguration redisCacheConfiguration() {
    //加载redis缓存的默认配置
    RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(timeToLive);
    configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    return configuration;
  }
}
