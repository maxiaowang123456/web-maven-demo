package com.company.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AspectJConfig {

    /**
     * AOP切面编程，同时传递参数
     * @param realName
     * @param pageNo
     * @param pageSize
     */
    @Pointcut("execution(* com.company.service.impl.EmployeeServiceImpl.*(..))&&args(realName,pageNo,pageSize)")
    public void pointcut(String realName,Integer pageNo,Integer pageSize){};


    @Before("pointcut(realName,pageNo,pageSize)")
    public void before(String realName,Integer pageNo,Integer pageSize){
        System.out.println("before method invoke======"+realName);
    }

    @After("pointcut(realName,pageNo,pageSize)")
    public void after(String realName,Integer pageNo,Integer pageSize){
        System.out.println("after method invoke======"+realName);
    }
    @AfterReturning("pointcut(realName,pageNo,pageSize)")
    public void afterReturn(String realName,Integer pageNo,Integer pageSize){
        System.out.println("after return======"+realName);
    }
    @AfterThrowing("pointcut(realName,pageNo,pageSize)")
    public void afterThrowing(String realName,Integer pageNo,Integer pageSize){
        System.out.println("after throwing======"+realName);
    }

    @Around("pointcut(realName,pageNo,pageSize)")
    public void around(ProceedingJoinPoint joinPoint,String realName,Integer pageNo,Integer pageSize){
        System.out.println("before around======"+realName);
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("around catch exception======"+realName);
        }
        System.out.println("after around======"+realName);
    }
}
