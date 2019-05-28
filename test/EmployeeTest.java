import com.company.mapper.EmployeeMapper;
import com.company.pojo.Employee;
import com.company.pojo.ProcEmployee;
import com.company.pojo.SexEnum;
import com.company.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-mybatis.xml"})
public class EmployeeTest {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testGetEmployee(){
        Employee employee=employeeMapper.getEmployee(1L);
        System.out.println(employee.getId()+":"+employee.getEmployeeTaskList().size());
    }

    @Test
    public void testGetEmployeeCascade(){
        Employee employee=new Employee();
        employee.setRealName("zs");
        Employee emp=employeeMapper.getEmployeeByCascade(employee);
        System.out.println(emp.getId()+":"+emp.getRealName());
        System.out.println(emp.getEmployeeTaskList().size());
    }

  //  @Test
    public void testSaveEmployee(){
        Employee employee=new Employee();
        employee.setRealName("lisi");
        employee.setBirthday(new Date());
        employee.setEmail("123456@163.com");
        employee.setMobile("15033062033");
        employee.setPosition("beijing");
        employee.setSex(SexEnum.MALE);
        employeeMapper.save(employee);
        System.out.println(employee.getId());
    }

    @Test
    public void testGetEmployeeList(){
        List<Integer> ids=Arrays.asList(1,2);
        List<Employee>employeeList=employeeMapper.getEmployeeList(ids);
        for(Employee employee:employeeList){
            System.out.println(employee.getId()+":"+employee.getRealName());
        }
    }

    @Test
    public void testQueryEmployeeByPage(){
        employeeService.queryByPage("lisi",2,10);
    }

    /**
     * MyBatis存储过程调用测试
     */
    @Test
    public void testProcEmployeeByCallable(){
        ProcEmployee procEmployee=new ProcEmployee();
        procEmployee.setRealName("zs");
        employeeMapper.countEmployee(procEmployee);
        System.out.println(procEmployee.getTotal()+":"+procEmployee.getExeDate());
    }

    /**
     * MyBatis调用存储过程返回多个结果集
     */
    @Test
    public void testGetEmployeeListByCallable(){
        Map<String,Object> params=new HashMap<>();
        params.put("realName","zs");
        List<Employee>employeeList=employeeMapper.getEmployListByProc(params);
        System.out.println(employeeList.get(0).getRealName()+"==="+employeeList.get(0).getEmail());
    }
}
