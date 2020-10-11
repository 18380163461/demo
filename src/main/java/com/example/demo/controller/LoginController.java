package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.demo.dao.mapper.bo.Dep;
import com.example.demo.dao.mapper.bo.User;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("login")
public class LoginController {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


  @GetMapping(value = "/session")
  public Map<String, Object> getSession(HttpServletRequest request, String userName, String passWord) {
    // 添加数据到Session
    HttpSession httpSession = request.getSession();
    User user = new User();
    user.setName(userName);
    user.setAge(99);
    user.setId(1);
    Dep dep=new Dep();
    dep.setId("11111");
    dep.setName("AAAAAAAA");
    user.setDep(dep);
    httpSession.setAttribute("userInfo", JSON.toJSONString(user));
    // 添加sessionID到Map
    Map<String, Object> map = new HashMap<>();
    map.put("sessionId", request.getSession().getId());
    return map;
  }

  @GetMapping(value = "/get")
  public Map get(HttpServletRequest request) {
    // 获取Session数据
    HttpSession httpSession = request.getSession();
    String userInfo = (String) httpSession.getAttribute("userInfo");
    User user = JSON.parseObject(userInfo, new TypeReference<User>() {
    });
    LOGGER.debug("{}", user);
    Map<String, Object> map = new HashMap<>();
    map.put("sessionId", httpSession.getId());
    map.put("userInfo", userInfo);
    return map;
  }

  @GetMapping(value = "/logout")
  public String logout(HttpServletRequest request) {
    // 销毁sessioin
    request.getSession().invalidate();
    return "ok";
  }
}
