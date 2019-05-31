package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String main(){
        return "main/index";
    }

    @RequestMapping("/error")
    public String error(){
        throw new RuntimeException();
    }
    @RequestMapping("/locale")
    public String locale(){
        return "main/locale";
    }
}
