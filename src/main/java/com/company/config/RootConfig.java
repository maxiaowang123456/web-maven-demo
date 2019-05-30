package com.company.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages={"com.company"},
excludeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)})
@ImportResource(locations = {"classpath:spring-mybatis.xml"})
@EnableAspectJAutoProxy
public class RootConfig {

    @Bean
    public AspectJConfig aspectJConfig(){
        return new AspectJConfig();
    }
}
