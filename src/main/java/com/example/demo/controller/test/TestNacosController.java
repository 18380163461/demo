package com.example.demo.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("aaaaaa")
//@NacosPropertySource(dataId = "springboottest", groupId = "DEFAULT_GROUP", autoRefreshed = true)
public class TestNacosController {

  //  @NacosValue(value = "${server.port:AAAAAAAAAAAAAA}", autoRefreshed = true)
  private String serverName;

  @GetMapping("test2")
  @ResponseBody
  public String get() {
    return serverName;
  }


}
