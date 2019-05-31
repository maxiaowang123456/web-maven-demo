package com.company.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"com.company.controller"})
public class ExceptionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public String handleException(){
        return "main/exception";
    }
}
