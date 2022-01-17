package com.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Users {
    @Id
    private Integer userId;
    @Column
    private String email;
    @Column
    private String password;
}
