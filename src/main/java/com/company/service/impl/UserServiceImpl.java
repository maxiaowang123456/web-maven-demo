package com.company.service.impl;

import com.company.mapper.UserMapper;
import com.company.pojo.User;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
