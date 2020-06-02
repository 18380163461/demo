package com.example.demo.service.interfaces;

import com.example.demo.dao.mapper.bo.User;
import com.example.demo.model.BaseResponse;
import com.example.demo.model.PageResult;
import com.example.demo.model.QueryUsersRequest;

public interface IUserService {

  BaseResponse<PageResult<User>> queryUsers(QueryUsersRequest request);

  BaseResponse<Integer> addUser(QueryUsersRequest request);

  BaseResponse<Integer> deleteByPrimaryKey(Integer id);

  BaseResponse<User> selectByPrimaryKey(Integer id);

}
