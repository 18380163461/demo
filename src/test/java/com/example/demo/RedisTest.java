package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
class RedisTest {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;//操作key-value都是字符串

  @Autowired
  private RedisTemplate redisTemplate;//操作key-value都是对象
  private static final Logger LOGGER = LoggerFactory.getLogger(RedisTest.class);

  /**
   * Redis常见的五大数据类型： stringRedisTemplate.opsForValue();[String(字符串)] stringRedisTemplate.opsForList();[List(列表)] stringRedisTemplate.opsForSet();[Set(集合)] stringRedisTemplate.opsForHash();[Hash(散列)] stringRedisTemplate.opsForZSet();[ZSet(有序集合)]
   */

  @Test
  void contextLoads() {
    stringRedisTemplate.opsForValue().append("msg", "hello");

  }

  @Test
  void get() {
    String data = stringRedisTemplate.opsForValue().get("msg");
    LOGGER.debug(data);
  }
}
