package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo"})
@MapperScan(value = "com.example.demo.dao")
@EnableCaching  //开启缓存
@EnableRedisHttpSession     // 启用ResisSession存储
public class DemoApplication {

  public static void main(String[] args) {
    System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
    System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
    SpringApplication.run(DemoApplication.class, args);
  }

}
