package com.company.mapper;
import com.company.pojo.User;

public interface UserMapper {

     User getUser(String username);
     int saveUser(User user);
}
