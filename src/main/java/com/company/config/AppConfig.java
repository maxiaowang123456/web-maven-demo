package com.company.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

public class AppConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        String filePath="E:/uploads";
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
        Long fileSize=(long)(5*Math.pow(2,20));
        Long requestSize=(long)(10*Math.pow(2,20));
        registration.setMultipartConfig(new MultipartConfigElement(filePath,fileSize,requestSize,0));
        super.customizeRegistration(registration);
    }
}
