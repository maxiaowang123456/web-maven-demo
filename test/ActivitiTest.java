import com.company.config.RootConfig;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class ActivitiTest {
    Logger logger=LogManager.getLogger(this.getClass());

    @Autowired
    private ProcessEngineFactoryBean processEngine;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ManagementService managementService;

    @Test
    public void deployProcessDefination(){
        Deployment deployment=repositoryService.createDeployment().name("activiti test")
                .addClasspathResource("activity/test.bpmn").deploy();
        System.out.println("部署ID:"+deployment.getId());
        System.out.println("部署名称："+deployment.getName());
    }

    @Test
    public void startProcessInstance(){
        String key="helloprocess";
        ProcessInstance instance=runtimeService.startProcessInstanceByKey(key);
        System.out.println("流程ID："+instance.getId());
        System.out.println("流程定义Id："+instance.getProcessDefinitionId());
    }
    @Test
    public void findByPersonalTask(){
        String assignee="lisi";
        List<Task>taskList= taskService.createTaskQuery().taskAssignee(assignee).list();
        for(Task task:taskList){
            System.out.println("任务Id："+task.getId());
            System.out.println("任务名称:" + task.getName());
            System.out.println("任务的创建时间:" + task.getCreateTime());
            System.out.println("任务的办理人:" + task.getAssignee());
            System.out.println("流程实例ID:" + task.getProcessInstanceId());
            System.out.println("执行对象ID:" + task.getExecutionId());
            System.out.println("流程定义ID:" + task.getProcessDefinitionId());
        }
    }
}
