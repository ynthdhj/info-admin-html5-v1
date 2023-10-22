package com.info.admin.html5.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
/**
 * 拦截器配置
 * 充许上传文件的 http 访问， static 是工程目录，即项目打包后 jar 目录  
 * @author 段洪杰
 **/
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
 
   
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/","file:static/");
    }
}
