package com.example.demo.service.interfaces;

import com.example.demo.dao.mapper.bo.User;
import com.example.demo.model.QueryUsersRequest;
import java.util.List;

public interface IUserService {

  List<User> queryUsers(QueryUsersRequest request);

  int addUser(QueryUsersRequest request);

}
