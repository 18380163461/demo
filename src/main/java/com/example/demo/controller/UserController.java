package com.example.demo.controller;

import com.example.demo.dao.mapper.bo.User;
import com.example.demo.model.QueryUsersRequest;
import com.example.demo.service.interfaces.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "用户操作接口")
@RestController("user")
public class UserController {

  @Autowired
  IUserService iUserService;

  @ApiOperation(value = "value 查询所有用户", notes = "notes查询所有用户")
  @GetMapping("queryUsers")
  public List<User> queryUsers(QueryUsersRequest request) {
    List<User> users = iUserService.queryUsers(request);
    return users;
  }

  @ApiOperation(value = "value 新增")
  @PostMapping("addUser")
  public int addUser(QueryUsersRequest request) {
    try {
      int users = iUserService.addUser(request);
      return users;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

}
