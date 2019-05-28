package com.company.service.impl;

import com.company.mapper.EmployeeMapper;
import com.company.pojo.Employee;
import com.company.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public PageInfo<Employee> queryByPage(String realName, Integer pageNo, Integer pageSize) {
        pageNo=pageNo==null?1:pageNo;
        pageSize=pageSize==null?15:pageSize;
        PageHelper.startPage(pageNo,pageSize);
        List<Employee>employeeList=employeeMapper.getEmployeeByRealName(realName);
        PageInfo<Employee>pageInfo=new PageInfo<>(employeeList);
        System.out.println("当前页数："+pageInfo.getPageNum());
        System.out.println("每页显示记录数："+pageInfo.getPageSize());
        System.out.println("当前页开始行数："+pageInfo.getStartRow());
        System.out.println("当前页数结束行数："+pageInfo.getEndRow());
        System.out.println("总共记录数："+pageInfo.getTotal());
        System.out.println("总页数："+pageInfo.getPages());
        System.out.println("第一页："+pageInfo.getNavigateFirstPage());
        System.out.println("最后一页："+pageInfo.getNavigateLastPage());
        return pageInfo;
    }
}
