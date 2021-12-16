package com.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private Integer ID;
    @Column
    private String description;
    @Column
    private Date time;
    @Column
    private String email;
}
