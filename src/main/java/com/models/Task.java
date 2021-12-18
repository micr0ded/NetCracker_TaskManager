package com.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'X'")
    private Date time;
    @Column
    private String email;
}
