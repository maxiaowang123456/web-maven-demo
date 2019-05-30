package com.company.controller;

import com.company.pojo.User;
import com.company.service.UserService;
import com.company.view.ExcelExportService;
import com.company.view.ExcelView;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registry")
    public String registry(){
        return "main/userRegistry";
    }

    @RequestMapping(value="/saveUser")
    public String saveUser(User user){
        userService.saveUser(user);
        return "main/userList";
    }

    @RequestMapping(value = "/param")
    @ResponseBody
    public String generalParam(String name){
        return name;
    }

    @RequestMapping(value = "/param/{name}")
    @ResponseBody
    public String urlParam(@PathVariable String name){
        return name;
    }

    @RequestMapping(value = "/param/json")
    @ResponseBody
    public String jsonParam(User user){
        System.out.println(user.getUsername()+"======"+user.getPassword());
        return "{status:true}";
    }

    /**
     * 导出Excel请求
     * @param user
     * @return
     */
    @RequestMapping(value="/user/export",method = RequestMethod.GET)
    public ModelAndView export(User user){
        ModelAndView modelAndView=new ModelAndView();
        List<User>userList=userService.findUserList(user);
        modelAndView.addObject("userList",userList);
        //设置视图为Excel视图
        modelAndView.setView(new ExcelView(exportService()));
        return modelAndView;
    }

    public  ExcelExportService exportService(){
        //自定义解析Excel数据接口
        return new ExcelExportService() {
            @Override
            public void makeExcel(Map<String, Object> model, Workbook workbook) {
                List<User>userList=(List<User>)model.get("userList");
                Sheet sheet=workbook.createSheet("用户列表");
                Row title=sheet.createRow(0);
                title.createCell(0).setCellValue("编号");
                title.createCell(1).setCellValue("用户名");
                title.createCell(2).setCellValue("密码");
                for(int i=0;i<userList.size();i++){
                    User user=userList.get(i);
                    Row row=sheet.createRow(i+1);
                    row.createCell(0).setCellValue(user.getId());
                    row.createCell(1).setCellValue(user.getUsername());
                    row.createCell(2).setCellValue(user.getPassword());
                }
            }
        };
    }
}
