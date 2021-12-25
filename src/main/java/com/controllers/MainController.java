package com.controllers;
import com.models.Task;
import com.repo.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
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
    public Iterable<Task> findAllTasks(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam (value = "size", defaultValue = "3") int size){
        return taskRepository.findAll(PageRequest.of(page, size));
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

    @GetMapping("/tasks/search")
    public Iterable<Task> findTaskBySmth(@RequestParam (value = "q") String desc){
        return taskRepository.findTaskBySmth(desc);
    }

//    @GetMapping("/tasks/find/id")
//    public Iterable<Task> findTaskByID(@RequestParam (value = "id") int id) {
//        return taskRepository.findTaskByID(id);
//    }
//
//    @GetMapping("/tasks/find/time")
//    public Iterable<Task> findTaskByTime(@RequestParam (value = "time") long Time){
//        return taskRepository.findTaskByTime(Time);
//    }
}
