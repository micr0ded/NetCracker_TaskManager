package com.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
