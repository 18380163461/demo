package com.example.demo.atom.impl;

import com.example.demo.atom.interfaces.IUserAutoSV;
import com.example.demo.dao.mapper.bo.User;
import com.example.demo.dao.mapper.bo.UserExample;
import com.example.demo.dao.mapper.interfaces.UserMapper;
import com.example.demo.model.PageArg;
import com.example.demo.model.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Component
public class IUserAutoSVImpl implements IUserAutoSV {

  @Autowired
  UserMapper mapper;

  @Override
  public PageResult<User> selectByExample(UserExample example, User user, PageArg pageArg) {
    PageResult<User> result = new PageResult<>();
    if (ObjectUtils.isEmpty(pageArg) || pageArg.getPageNum() < 1 || pageArg.getPageSize() < 1) {
      pageArg = new PageArg(1, 10);
    }
    try {
      PageHelper.startPage(pageArg.getPageNum(), pageArg.getPageSize());
      List<User> list = mapper.selectByExample(buildExample(example, user));
      result.setResult(list);
      result.setSuccess(true);
      result.setPageNum(pageArg.getPageNum());
      result.setPageSize(pageArg.getPageSize());
      result.setCount(((Page<User>) list).getTotal());
    } finally {
      PageHelper.clearPage();
    }
    return result;
  }

  @Override
  public int addUser(User user) {
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
