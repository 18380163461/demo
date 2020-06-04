package com.example.demo.atom.interfaces;

import com.example.demo.dao.mapper.bo.User;
import com.example.demo.dao.mapper.bo.UserExample;
import com.example.demo.model.PageArg;
import com.example.demo.model.PageResult;

public interface IUserAutoSV {

  PageResult<User> selectByExample(UserExample example, User user, PageArg pageArg);

  int addUser(User user);

  int deleteByPrimaryKey(Integer id);

  User selectByPrimaryKey(Integer id);

  int updateByPrimaryKey(User record);

}
