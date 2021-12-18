package com.repo;

import com.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    @Query("select t.description, t.ID from Task t where t.description = ?1 or t.ID = ?1 or t.time = ?1 or t.email = ?1")
    Iterable<Task> findTaskByDescription(String desc);
    Iterable<Task> findTaskByTimeBefore(Date date);
    Page<Task> findAll(Pageable pageable);
}