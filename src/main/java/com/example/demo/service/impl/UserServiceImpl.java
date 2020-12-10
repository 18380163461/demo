package com.example.demo.service.impl;

import com.example.demo.atom.interfaces.IUserAutoSV;
import com.example.demo.constants.BusinessConstants;
import com.example.demo.dao.mapper.bo.User;
import com.example.demo.dao.mapper.bo.UserExample;
import com.example.demo.dao.mapper.bo.UserExample.Criteria;
import com.example.demo.model.BaseResponse;
import com.example.demo.model.PageResult;
import com.example.demo.model.QueryUsersRequest;
import com.example.demo.service.interfaces.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  IUserAutoSV iUserAutoSV;
  @Autowired
  RedisTemplate redisTemplate;

  @Override
  public BaseResponse<PageResult<User>> queryUsers(QueryUsersRequest request) {


    BaseResponse<PageResult<User>> response = new BaseResponse<>(true, BusinessConstants.BUSI_SUCCESS_CODE, BusinessConstants.BUSI_SUCCESS_MESSAGE);
    UserExample example = new UserExample();
    Criteria criteria = example.createCriteria();
    criteria.andNameLike("名称%");
    User user = new User();
    BeanUtils.copyProperties(request, user);
    PageResult<User> pageResult = iUserAutoSV.selectByExample(example, user, request.getPageArg());
    response.setResult(pageResult);
    return response;
  }

  @Override
  public BaseResponse<Integer> addUser(QueryUsersRequest request) {
    BaseResponse<Integer> response = new BaseResponse<>(true, BusinessConstants.BUSI_SUCCESS_CODE, BusinessConstants.BUSI_SUCCESS_MESSAGE);
    User user = new User();
    BeanUtils.copyProperties(request, user);
    int result = iUserAutoSV.addUser(user);
    if (request.getName().contains("A")) {
      throw new RuntimeException("异常");
    }
    response.setResult(result);
    return response;
  }

  @Override
  public BaseResponse<Integer> deleteByPrimaryKey(Integer id) {
    BaseResponse<Integer> response = new BaseResponse<>(true, BusinessConstants.BUSI_SUCCESS_CODE, BusinessConstants.BUSI_SUCCESS_MESSAGE);
    int result = iUserAutoSV.deleteByPrimaryKey(id);
    response.setResult(result);
    return response;
  }

  @Override
  @Cacheable(value = "User", key = "#id")
  public BaseResponse<User> selectByPrimaryKey(Integer id) {
    BaseResponse<User> response = new BaseResponse<>(true, BusinessConstants.BUSI_SUCCESS_CODE, BusinessConstants.BUSI_SUCCESS_MESSAGE);
    User result = iUserAutoSV.selectByPrimaryKey(id);
    response.setResult(result);
    return response;
  }

  @Override
  @CachePut(value = "User", key = "#record.id")
  public BaseResponse<User> updateByPrimaryKey(User record) {
    BaseResponse<User> response = new BaseResponse<>(true, BusinessConstants.BUSI_SUCCESS_CODE, BusinessConstants.BUSI_SUCCESS_MESSAGE);
    int result = iUserAutoSV.updateByPrimaryKey(record);
    response.setResult(record);
    return response;
  }
}
