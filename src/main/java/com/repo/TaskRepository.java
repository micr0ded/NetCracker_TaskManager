package com.repo;

import com.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    @Query("select t from Task t where t.description = ?1 or t.ID= ?1 or t.time = ?1")
    Iterable<Task> findTaskBySmth(String desc);
    @Query("select t.description, t.ID from Task t where t.description = ?1 or t.ID = ?1 or t.time = ?1 or t.userId = ?1")
    Iterable<Task> findTaskByDescription(String desc);
    Iterable<Task> findTaskByTimeBefore(Date date);
    Iterable<Task> findAllByUserId(Integer id);
    Page<Task> findAll(Pageable pageable);
    Page<Task> findAllByUserId(Pageable pageable, Integer id);
    @Modifying
    @Query("update Task t set t.isSent = ?1 where t.ID = ?2")
    @Transactional
    void updateFlag(boolean flag, int id);
}