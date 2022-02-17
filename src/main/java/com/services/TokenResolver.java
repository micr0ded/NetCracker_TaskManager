package com.services;

import com.annotations.UserId;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.models.Token;
import com.repo.UsersRepository;
import com.services.JWTAlgoService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.List;

@Service
public class TokenResolver implements HandlerMethodArgumentResolver {
    private final UsersRepository usersRepository;
    public static List<Token> templatesTokens = new ArrayList<>();

    public TokenResolver(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserId.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory
    ) throws Exception {
        Integer userId;
        String token = webRequest.getParameter("token");
        JWTVerifier verifier = JWTAlgoService.getVerifier();
        if(token != null) {
            DecodedJWT jwt = JWTAlgoService.getVerifiedJWT(token);
            userId = jwt.getClaim("userId").asInt();
            templatesTokens.add(new Token(token));
        }
        else {
            DecodedJWT jwt = JWTAlgoService.getVerifiedJWT(templatesTokens.get(templatesTokens.size() - 1).getData());
            userId = jwt.getClaim("userId").asInt();
        }
        return userId;
    }
}