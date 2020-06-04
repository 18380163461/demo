package com.example.demo.controller;

import com.example.demo.constants.BusinessConstants;
import com.example.demo.dao.mapper.bo.User;
import com.example.demo.model.BaseResponse;
import com.example.demo.model.PageResult;
import com.example.demo.model.QueryUsersRequest;
import com.example.demo.service.interfaces.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "用户操作接口")
@RestController("user")
public class UserController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
  @Autowired
  IUserService iUserService;

  @ApiOperation(value = "value 查询所有用户", notes = "notes查询所有用户")
  @GetMapping("queryUsers")
  public BaseResponse queryUsers(QueryUsersRequest request) {
    BaseResponse<PageResult<User>> users = iUserService.queryUsers(request);
    return users;
  }

  @ApiOperation(value = "value 新增")
  @PostMapping("addUser")
  public BaseResponse addUser(QueryUsersRequest request) {
    BaseResponse<Integer> response = null;
    try {
      response = iUserService.addUser(request);
    } catch (Exception e) {
      LOGGER.error("{}", e.getMessage(), e);
      response = new BaseResponse<>(false, BusinessConstants.BUSI_FAILURE_CODE, BusinessConstants.BUSI_FAILURE_MESSAGE);
    }
    return response;
  }

  Random random = new Random();

  @GetMapping("queryUserByRandom")
  public BaseResponse queryUserByRandom() {
    int id = random.nextInt(1000000);
    BaseResponse<User> response = null;
    try {
      response = iUserService.selectByPrimaryKey(id);
    } catch (Exception e) {
      LOGGER.error("{}", e.getMessage(), e);
      response = new BaseResponse<>(false, BusinessConstants.BUSI_FAILURE_CODE, BusinessConstants.BUSI_FAILURE_MESSAGE);
    }
    return response;
  }

  @GetMapping("queryUserByid")
  public BaseResponse queryUserByid(Integer id) {
    BaseResponse<User> response = null;
    try {
      response = iUserService.selectByPrimaryKey(id);
    } catch (Exception e) {
      LOGGER.error("{}", e.getMessage(), e);
      response = new BaseResponse<>(false, BusinessConstants.BUSI_FAILURE_CODE, BusinessConstants.BUSI_FAILURE_MESSAGE);
    }
    return response;
  }

  @PutMapping("updateUserByid")
  public BaseResponse updateUserByid(User user) {
    BaseResponse<User> response = null;
    try {
      response = iUserService.updateByPrimaryKey(user);
    } catch (Exception e) {
      LOGGER.error("{}", e.getMessage(), e);
      response = new BaseResponse<>(false, BusinessConstants.BUSI_FAILURE_CODE, BusinessConstants.BUSI_FAILURE_MESSAGE);
    }
    return response;
  }

  @GetMapping("deleteById")
  public BaseResponse deleteById(Integer id) {
    BaseResponse<Integer> response = null;
    try {
      response = iUserService.deleteByPrimaryKey(id);
    } catch (Exception e) {
      LOGGER.error("{}", e.getMessage(), e);
      response = new BaseResponse<>(false, BusinessConstants.BUSI_FAILURE_CODE, BusinessConstants.BUSI_FAILURE_MESSAGE);
    }
    return response;
  }
}
