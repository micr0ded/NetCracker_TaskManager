package com.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Token {
    private Date creatingTime;
    private String data;

    public Token(String data){
        this.data = data;
        this.creatingTime = new Date();
    }
}
