import com.company.config.RootConfig;
import com.company.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class AspectJTest {
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testAspectJ(){
        employeeService.queryByPage("lisi",2,10);
    }
}
