package com.example.demo.atom.interfaces;

import com.example.demo.dao.mapper.bo.User;
import com.example.demo.model.QueryUsersRequest;
import java.util.List;

public interface IUserAutoSV {

  List<User> selectByExample(QueryUsersRequest request);

  int addUser(QueryUsersRequest request);
}
