package com.schedulers;

import com.models.Token;
import com.services.TokenResolver;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class TokenScheduler {
    @Scheduled(fixedDelay = 10000)
    public void run() {
        Date tmpDate = new Date();
        for(Token tmp: TokenResolver.templatesTokens){

        }
    }
}