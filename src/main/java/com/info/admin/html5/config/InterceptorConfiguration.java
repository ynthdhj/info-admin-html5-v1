package com.info.admin.html5.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */

/**
 * 拦截器配置
 * 充许上传文件的 http 访问， static 是工程目录，即项目打包后 jar 目录  

 **/
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
 
   
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/","file:static/");
    }
}
