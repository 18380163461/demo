package com.example.demo.service.impl;

import com.example.demo.atom.interfaces.IUserAutoSV;
import com.example.demo.constants.BusinessConstants;
import com.example.demo.dao.mapper.bo.User;
import com.example.demo.dao.mapper.bo.UserExample;
import com.example.demo.model.BaseResponse;
import com.example.demo.model.PageResult;
import com.example.demo.model.QueryUsersRequest;
import com.example.demo.service.interfaces.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  IUserAutoSV iUserAutoSV;

  @Override
  public BaseResponse<PageResult<User>> queryUsers(QueryUsersRequest request) {
    BaseResponse<PageResult<User>> response = new BaseResponse<>(true, BusinessConstants.BUSI_SUCCESS_CODE, BusinessConstants.BUSI_SUCCESS_MESSAGE);
    UserExample example = new UserExample();
    User user = new User();
    BeanUtils.copyProperties(request, user);
    PageResult<User> pageResult = iUserAutoSV.selectByExample(example, user, request.getPageArg());
    response.setResult(pageResult);
    return response;
  }

  @Override
  public int addUser(QueryUsersRequest request) {
    User user = new User();
    BeanUtils.copyProperties(request, user);
    int result = iUserAutoSV.addUser(user);
    if (request.getName().contains("A")) {
      throw new RuntimeException("异常");
    }
    return result;
  }
}
