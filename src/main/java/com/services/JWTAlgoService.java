package com.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAlgoService {
    public static JWTVerifier getVerifier(){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
        return verifier;
    }

    public static DecodedJWT getVerifiedJWT(String token){
        JWTVerifier verifier = JWTAlgoService.getVerifier();
        DecodedJWT jwt = verifier.verify(token);
        return jwt;
    }
}
