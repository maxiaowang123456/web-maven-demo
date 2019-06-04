package com.company.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages={"com.company"},
excludeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)})
@ImportResource(locations = {"classpath:spring-mybatis.xml"})
@PropertySource(value = {"classpath:dataSource.properties"})
@EnableAspectJAutoProxy
public class RootConfig {

    @Bean
    public AspectJConfig aspectJConfig(){
        return new AspectJConfig();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}
