package com.info.admin.html5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/*
 *  @author 段洪杰
 */
@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    @Autowired
    private UserDetailsService adminDetailsService;
                               
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(mvc.pattern("/assets/admin/**")).permitAll()
                                 .requestMatchers(mvc.pattern("/assets/ckeditor/**")).permitAll()
                                 .requestMatchers(mvc.pattern("/static/**")).permitAll()   // 上传文件的访问权， static 是工程目录，即项目打包后 jar 目录
                                 
                                 .requestMatchers(mvc.pattern("/admin/login")).permitAll()
                                 .requestMatchers(mvc.pattern("/admin/logout")).permitAll()
                                 .requestMatchers(mvc.pattern("/admin/**")).authenticated()
                                
                                
                ).formLogin(
                        form -> form
                                .loginPage("/admin/login")
                                .loginProcessingUrl("/admin/login")
                                .defaultSuccessUrl("/admin/dashboard",true)
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(adminDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    
    @Scope("prototype")
	@Bean
	MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
		return new MvcRequestMatcher.Builder(introspector);
	}
}
