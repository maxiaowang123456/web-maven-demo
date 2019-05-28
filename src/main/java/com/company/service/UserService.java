package com.company.service;

import com.company.mapper.UserMapper;
import com.company.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserByUsername(String username){
        return userMapper.getUser(username);
    }

}
