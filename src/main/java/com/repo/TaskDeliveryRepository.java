package com.repo;

import com.models.TaskDelivery;
import org.springframework.data.repository.CrudRepository;

public interface
TaskDeliveryRepository extends CrudRepository<TaskDelivery, Integer> {
    TaskDelivery findByUserId(int id);
}