package com.example.demo.controller;

import com.example.demo.constants.BusinessConstants;
import com.example.demo.model.BaseResponse;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("login")
public class LoginController {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

  @GetMapping("login")
  public BaseResponse login(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,
      String userName, String passWord) {
    BaseResponse response = new BaseResponse(true, BusinessConstants.BUSI_SUCCESS_CODE, BusinessConstants.BUSI_SUCCESS_MESSAGE);
    httpServletRequest.getCookies();
    HttpSession httpSession = httpServletRequest.getSession();
    if (passWord.equals("123456")) {
      Cookie cookie = new Cookie("userName", userName);
      cookie.setMaxAge(60 * 60 * 24 * 7);
      httpServletResponse.addCookie(cookie);
      return response;
    }
    return response;
  }

  @GetMapping(value = "/session")
  public Map<String, Object> getSession(HttpServletRequest request, String userName, String passWord) {
    // 添加数据到Session
    HttpSession httpSession = request.getSession();
    httpSession.setAttribute("username", userName);
    // 添加sessionID到Map
    Map<String, Object> map = new HashMap<>();
    map.put("sessionId", request.getSession().getId());
    return map;
  }

  @GetMapping(value = "/get")
  public Map get(HttpServletRequest request) {
    // 获取Session数据
    HttpSession httpSession = request.getSession();
    String userName = (String) httpSession.getAttribute("username");
    Map<String, Object> map = new HashMap<>();
    map.put("sessionId", httpSession.getId());
    map.put("username", userName);
    return map;
  }

  @GetMapping(value = "/logout")
  public String logout(HttpServletRequest request) {
    // 销毁sessioin
    request.getSession().invalidate();
    return "ok";
  }
}
