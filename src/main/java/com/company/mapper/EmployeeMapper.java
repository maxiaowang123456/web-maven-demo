package com.company.mapper;

import com.company.pojo.Employee;
import com.company.pojo.ProcEmployee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    Employee getEmployee(Long id);
    List<Employee>getEmployeeByRealName(String realName);
    Employee getEmployeeByCascade(Employee employee);
    int save(Employee employee);
    List<Employee> getEmployeeList(@Param("ids") List<Integer>ids);
    void countEmployee(ProcEmployee procEmployee);
    List getEmployListByProc( Map<String,Object> params);

}
