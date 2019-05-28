package com.company.mapper;

import com.company.pojo.EmployeeTask;

public interface EmployeeTaskMapper {
    EmployeeTask getEmployeeTaskByEmpId(Long empId);
}
