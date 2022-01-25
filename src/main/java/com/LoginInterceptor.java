package com;

import com.controllers.WebController;
import com.models.Users;
import com.repo.UsersRepository;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    private final UsersRepository usersRepository;

    public LoginInterceptor(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String password = WebController.currentUser.getPassword();
        String email = WebController.currentUser.getEmail();
        Users user = usersRepository.findByEmail(email);
        if (user == null || !user.getPassword ().equals (password)) {
            throw new Exception("Invalid User email or Password. Please try again.");
        }
        WebController.currentUser = user;
        return true;
    }
}
