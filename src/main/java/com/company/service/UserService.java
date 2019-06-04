package com.company.service;

import com.company.pojo.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
   User findUserByUsername(String username);
   User saveUser(User user);
   PageInfo<User>findUserListByPage(User user,Integer pageNo,Integer pageSize);
   public List<User> findUserList(User user);
   User findUserById(Long id);
   int deleteUser(Long id);
}
