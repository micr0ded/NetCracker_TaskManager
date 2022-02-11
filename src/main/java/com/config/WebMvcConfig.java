package com.config;

import com.LoginInterceptor;
import com.TokenResolver;
import com.repo.UsersRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    private final UsersRepository usersRepository;

    public WebMvcConfig(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(usersRepository)).addPathPatterns("/home", "/add");
    }
}
