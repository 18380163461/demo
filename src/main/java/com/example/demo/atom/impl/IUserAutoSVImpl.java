package com.example.demo.atom.impl;

import com.example.demo.atom.interfaces.IUserAutoSV;
import com.example.demo.dao.mapper.bo.User;
import com.example.demo.dao.mapper.bo.UserExample;
import com.example.demo.dao.mapper.bo.UserExample.Criteria;
import com.example.demo.dao.mapper.interfaces.UserMapper;
import com.example.demo.model.QueryUsersRequest;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Component
public class IUserAutoSVImpl implements IUserAutoSV {

  @Autowired
  UserMapper mapper;

  @Override
  public List<User> selectByExample(QueryUsersRequest request) {
    User user = new User();
    BeanUtils.copyProperties(request, user);
    UserExample example = new UserExample();
    Criteria criteria = example.createCriteria();
    return mapper.selectByExample(buildExample(example, user));
  }

  @Override
  public int addUser(QueryUsersRequest request) {
    User user = new User();
    BeanUtils.copyProperties(request, user);
    return mapper.insert(user);
  }

  private UserExample buildExample(UserExample example, User obj) {
    if (ObjectUtils.isEmpty(obj)) {
      return example;
    }
    UserExample.Criteria criteria;
    if (ObjectUtils.isEmpty(example)) {
      example = new UserExample();
      criteria = example.createCriteria();
    } else {
      List<UserExample.Criteria> criteriaList = example.getOredCriteria();
      if (!CollectionUtils.isEmpty(criteriaList)) {
        criteria = criteriaList.get(0);
      } else {
        criteria = example.createCriteria();
      }
    }
    //build_criteria
    if (!ObjectUtils.isEmpty(obj.getId())) {
      criteria.andIdEqualTo(obj.getId());
    }
    if (!ObjectUtils.isEmpty(obj.getName())) {
      criteria.andNameEqualTo(obj.getName());
    }
    if (!ObjectUtils.isEmpty(obj.getAge())) {
      criteria.andAgeEqualTo(obj.getAge());
    }
    return example;
  }

}
