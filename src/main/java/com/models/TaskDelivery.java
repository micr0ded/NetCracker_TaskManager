package com.models;

import com.DeliveryTypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TaskDelivery {
    @Id
    private Integer Id;
    @Column
    private DeliveryTypes deliveryTypes;
    @Column
    private Integer userId;
}
