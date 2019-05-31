package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {

    @RequestMapping("/upload")
    public ModelAndView upload(@RequestParam("files") MultipartFile[] files){
        ModelAndView mv=new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        boolean uploadFlag=true;
        for(MultipartFile tmpFile:files){
            uploadFlag=saveFile(tmpFile);
        }
        if(uploadFlag){
            mv.addObject("msg","上传成功");
            mv.addObject("success",true);
        }else{
            mv.addObject("msg","上传失败");
            mv.addObject("success",false);
        }
        return mv;
    }

    public boolean saveFile(MultipartFile file){
        if(!file.isEmpty()){
            try {
                String fileName=file.getOriginalFilename();
                //页面<%@page%>和<meta>中设置编码为UTF-8，后台用new String(fileName.getBytes(),"UTF-8")重新进行解码
                fileName=new String(fileName.getBytes(),"UTF-8");
                File dest=new File(fileName);
                file.transferTo(dest);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @RequestMapping("/uploadForm")
    public String uploadForm(){
        return "main/upload";
    }
}
