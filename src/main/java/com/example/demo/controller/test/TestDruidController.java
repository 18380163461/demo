package com.example.demo.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("TestDruid")
public class TestDruidController {

  private String serverName = "AAAAAAAA";

  @GetMapping(value = "/test1")
  @ResponseBody
  public String get() {
    return serverName;


  }


}
