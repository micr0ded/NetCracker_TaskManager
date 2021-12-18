package com.controllers;
import com.models.TaskPageContent;
import com.models.Task;
import com.repo.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MainController {
    private TaskRepository taskRepository;

    public MainController(TaskRepository repository){
        this.taskRepository = repository;
    }

    @GetMapping("/tasks/{id}")
    public Optional<Task> findTask(@PathVariable(value = "id") int id) {
        return taskRepository.findById(id);
    }

    @PostMapping("/tasks")
    public void addTask(@RequestBody Task task){
        taskRepository.save(task);
    }


    @GetMapping("/tasks")
    public TaskPageContent findAllTasks(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam (value = "size", defaultValue = "3") int size){
        return new TaskPageContent(taskRepository.findAll(PageRequest.of(page, size)).getContent(), taskRepository.findAll(PageRequest.of(page, size)).getSize());
    }

    @DeleteMapping("/tasks/{id}")
    public void removeTask(@PathVariable(value = "id") int id){
        taskRepository.deleteById(id);
    }

    @DeleteMapping("/tasks")
    public void removeAllTasks(){
        taskRepository.deleteAll();
    }

    @PutMapping("/tasks/{id}")
    public void editTask(@PathVariable(value = "id")int id, @RequestBody Task task){
        taskRepository.save(task);
    }

    @GetMapping("/tasks/find")
    public Iterable<Task> findTasByDesc(@RequestParam (value = "desc") String desc){
        return taskRepository.findTaskByDescription(desc);
    }

}
