package com.company.service;

import com.company.pojo.Employee;
import com.github.pagehelper.PageInfo;

public interface EmployeeService {
    PageInfo<Employee> queryByPage(String realName,Integer pageNo,Integer pageSize);
}
