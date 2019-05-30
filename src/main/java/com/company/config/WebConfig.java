package com.company.config;

import com.company.interceptors.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.company.controller"})
public class WebConfig implements WebMvcConfigurer {



    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver=
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setExposeContextBeansAsAttributes(true);
        viewResolver.setOrder(2);
        return viewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer configurer=new TilesConfigurer();
        configurer.setDefinitions("/WEB-INF/layout/tiles.xml");
        configurer.setCheckRefresh(true);
        return configurer;
    }
    @Bean
    public ViewResolver tileViewResolver(){
        TilesViewResolver viewResolver=new TilesViewResolver();
        viewResolver.setOrder(1);
        return viewResolver;
    }

    @Bean
    public HandlerInterceptor userHandlerInterceptor(){
        return new UserInterceptor();
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * SpringMvc设置请求URL拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userHandlerInterceptor()).addPathPatterns("/param/*");
    }
}
