package com.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Users {
    @Id
    private Integer userId;
    @Column
    private String email;
}
