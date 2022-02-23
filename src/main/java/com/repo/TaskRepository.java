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
    @Query("select t from Task t where t.name = ?1 or t.ID= ?1 or t.time = ?1")
    Iterable<Task> findTaskBySmth(String desc);
    @Query("select t.name, t.ID from Task t where t.name = ?1 or t.ID = ?1 or t.time = ?1 or t.userId = ?1")
    Iterable<Task> findTaskByName(String name);
    Iterable<Task> findTaskByTimeBefore(Date date);
    Page<Task> findAll(Pageable pageable);

    @Modifying
    @Query("update Task t set t.isSent = ?1 where t.ID = ?2")
    @Transactional
    void updateFlag(boolean flag, int id);

    @Modifying
    @Query("update Task t set t.isDone = ?1 where t.ID = ?2")
    @Transactional
    void updateDoneFlag(boolean flag, int id);

    @Modifying
    @Query("update Task t set t.isFailed = ?1 where t.ID = ?2")
    @Transactional
    void updateFailedFlag(boolean flag, int id);

    @Modifying
    @Query("update Task t set t.name = ?2, t.description = ?3, t.time = ?4 where t.ID = ?1")
    @Transactional
    void updateTask(int id, String name, String description, Date time);
}