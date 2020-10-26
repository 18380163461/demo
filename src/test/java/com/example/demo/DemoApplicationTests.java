package com.example.demo;

import java.util.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

  @Test
  void contextLoads() {
    byte[] decodedCookieBytes = Base64.getDecoder().decode("NjMxZTY1ODktMTE5ZC00ZjIwLTg2M2QtMWIzMDUyNWFlMDI0");
    System.out.println(new String(decodedCookieBytes));
  }

}
