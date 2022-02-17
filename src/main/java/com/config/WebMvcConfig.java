package com.config;


import com.services.TokenResolver;
import com.repo.UsersRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    private final UsersRepository usersRepository;
    private final TokenResolver tokenResolver;

    public WebMvcConfig(UsersRepository usersRepository, TokenResolver tokenResolver){
        this.usersRepository = usersRepository;
        this.tokenResolver = tokenResolver;
    }

/*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(usersRepository)).addPathPatterns("/home", "/add");
    }
*/

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(tokenResolver);
    }
}
