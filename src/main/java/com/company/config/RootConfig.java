package com.company.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages={"com.company"},
excludeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)})
@ImportResource(locations = {"classpath:spring-mybatis.xml"})
public class RootConfig {
}
