package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/iframe")
public class IframeController {

    @RequestMapping("/loadtext")
    public void loadText(HttpServletResponse response)throws IOException {
       StringBuilder sb=new StringBuilder("<html>\n" +
               "<body>\n" +
               "\n" +
               "<h1>My First Heading</h1>\n" +
               "\n" +
               "<p>My first paragraph.</p>\n" +
               "\n" +
               "</body>\n" +
               "</html>");
        response.getWriter().write(sb.toString());
    }

    @RequestMapping("/submit")
    public void submit(String code, HttpServletResponse response)throws  IOException{
        response.setContentType("text/html");
        code=code.replaceAll("demoscpt","script");
        response.getWriter().write(code);
    }
}
