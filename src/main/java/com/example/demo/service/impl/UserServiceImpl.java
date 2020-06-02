package com.example.demo.service.impl;

import com.example.demo.atom.interfaces.IUserAutoSV;
import com.example.demo.dao.mapper.bo.User;
import com.example.demo.dao.mapper.interfaces.UserMapper;
import com.example.demo.model.QueryUsersRequest;
import com.example.demo.service.interfaces.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  IUserAutoSV iUserAutoSV;
  @Autowired
  UserMapper mapper;

  @Override
  public List<User> queryUsers(QueryUsersRequest request) {
    return iUserAutoSV.selectByExample(request);
  }

  @Override
  public int addUser(QueryUsersRequest request) {
    int result = iUserAutoSV.addUser(request);
    if (request.getName().contains("A")) {
      throw new RuntimeException("异常");
    }
   /* User user = new User();
    BeanUtils.copyProperties(request, user);
    int result = mapper.insert(user);
    if (request.getName().contains("A")) {
      throw new RuntimeException("异常");
    }*/
    return result;
  }
}
