package com;

import java.util.List;

public interface Service {
    void addTask(Task task);
    Task getTask(int id);
    List<Task> getAllTasks();
}
