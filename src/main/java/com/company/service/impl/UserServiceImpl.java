package com.company.service.impl;

import com.company.mapper.UserMapper;
import com.company.pojo.User;
import com.company.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int saveUser(User user) {
        return userMapper.saveUser(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.getUser(username);
    }

    @Override
    public PageInfo<User> findUserListByPage(User user, Integer pageNo, Integer pageSize) {
        pageNo=pageNo==null?1:pageNo;
        pageSize=pageSize==null?10:pageSize;
        PageHelper.startPage(pageNo,pageSize);
        List<User>userList=userMapper.findUserList(user);
        PageInfo<User>pageInfo=new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public List<User> findUserList(User user){
        return userMapper.findUserList(user);
    }
}
