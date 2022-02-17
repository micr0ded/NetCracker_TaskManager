package com.models;

import com.enums.DeliveryTypes;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class TaskDelivery {
    @Id
    private Integer Id;
    @Column
    private DeliveryTypes deliveryTypes;
    @Column
    private Integer userId;
}
