package com;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TaskService implements Service {
    private final Storage storage;

    public TaskService(Storage storage){
        this.storage = storage;
    }

    @Override
    public Task getTask(int id) {
        return storage.getTask(id);
    }

    @Override
    public void addTask(Task task){
        storage.save(task);
    }

    @Override
    public List<Task> getAllTasks(){
        return  storage.readAllTasks();
    }

}
