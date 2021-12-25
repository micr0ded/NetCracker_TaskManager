package com.repo;

import com.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    @Query("select t from Task t where t.description = ?1 or t.ID= ?1 or t.time = ?1")
    Iterable<Task> findTaskBySmth(String desc);
//    @Query("select t.description, t.ID from Task t where t.ID = ?1")
//    Iterable<Task> findTaskByID(int id);
//    @Query("select t.description, t.ID from Task t where t.time = ?1")
//    Iterable<Task> findTaskByTime(long time);
    Page<Task> findAll(Pageable pageable);
}