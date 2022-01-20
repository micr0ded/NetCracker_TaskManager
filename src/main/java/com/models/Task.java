package com.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    @Column
    private String description;
    @Column
    @DateTimeFormat(pattern="yyyy-mm-dd HH:mm")
    private Date time;
    @Column
    private Integer userId;
    @Column
    private boolean isSent = false;

    public Task(String description, Date time, Integer userId) {
        this.description = description;
        this.time = time;
        this.userId = userId;
    }
}
