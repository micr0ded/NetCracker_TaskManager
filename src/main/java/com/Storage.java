package com;

import com.models.Task;

import java.util.List;

public interface Storage {
    void save(Task task);
    Task read();
    List<Task> readAllTasks();
    Task getTask(int id);
}
