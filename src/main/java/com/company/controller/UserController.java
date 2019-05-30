package com.company.controller;

import com.company.pojo.User;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registry")
    public String registry(){
        return "main/userRegistry";
    }

    @RequestMapping(value="/saveUser")
    public String saveUser(User user){
        userService.saveUser(user);
        return "main/userList";
    }
}
