package com.repo;

import com.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    @Query("select t.description, t.ID from Task t where t.description = ?1")
    Iterable<Task> findTaskByDescription(String desc);
    Task findTaskByTime(long time);
    Page<Task> findAll(Pageable pageable);
}