package com.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    private Integer userId;
    @Column
    private String email;
    @Column
    private String password;

    public Users(String email, String password){
        this.email = email;
        this.password = password;
    }
}
