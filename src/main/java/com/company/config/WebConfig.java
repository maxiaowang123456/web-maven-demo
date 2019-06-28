package com.company.config;

import com.company.interceptors.UserInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.company.controller"})
@EnableAsync
public class WebConfig extends AsyncConfigurerSupport implements WebMvcConfigurer {

    /**
     * 配置JSP视图解析器
     * @return
     */
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

    /**
     * 配置JSON与对象对转换消息类
     * @return
     */
    @Bean
    public RequestMappingHandlerAdapter handlerAdapter(){
        RequestMappingHandlerAdapter handlerAdapter=new RequestMappingHandlerAdapter();
        MappingJackson2HttpMessageConverter messageConverter=
                new MappingJackson2HttpMessageConverter();
        MediaType mediaType=MediaType.APPLICATION_JSON_UTF8;
        List<MediaType> mediaTypes=new ArrayList<>();
        mediaTypes.add(mediaType);
        messageConverter.setSupportedMediaTypes(mediaTypes);
        handlerAdapter.getMessageConverters().add(messageConverter);
        return handlerAdapter;
    }

    /**
     * 配置Tiles布局视图解析器
     * @return
     */
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

    /**
     * 配置文件上传请求解析器
     * @return
     */
    @Bean("multipartResolver")
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }

    @Bean
    public HandlerInterceptor userHandlerInterceptor(){
        return new UserInterceptor();
    }

    /**
     * 配置国际化资源文件加载
     * @return
     */
    @Bean("messageSource")
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:message");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(5);
        return messageSource;
    }

    /**
     * 配置国际化语言和区域环境解析器
     * @return
     */
    @Bean("localeResolver")
    public LocaleResolver localeResolver(){
        SessionLocaleResolver localeResolver=new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }

    /**
     * 配置拦截国际化请求参数名称
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor=new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
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
        registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/*");
    }

    @Override
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor taskExecutor=new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(200);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/img/");
    }
}
