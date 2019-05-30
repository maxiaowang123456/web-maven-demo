package com.company.mapper;

import com.company.pojo.User;

import java.util.List;

public interface UserMapper {

     User getUser(String username);
     int saveUser(User user);
     List<User>findUserList(User user);
}
