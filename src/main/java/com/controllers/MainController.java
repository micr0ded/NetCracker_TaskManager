package com.controllers;

import com.Service;
import com.models.Task;
import com.repo.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RestController
public class MainController {
    private final Service service;
    private TaskRepository taskRepository;

    public MainController(Service service, TaskRepository repository){
        this.service = service;
        this.taskRepository = repository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "username");
        return "home";
    }


    @GetMapping("/tasks/{id}")
    public Optional<Task> getTask(@PathVariable(value = "id") int id) {
        return taskRepository.findById(id);
    }

    @PostMapping("/tasks")
    public void addTask(@RequestBody Task task){
        taskRepository.save(task);
    }


    @GetMapping("/tasks")
    public Iterable<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @DeleteMapping("/tasks/{id}")
    public void removeTask(@PathVariable(value = "id") int id){
        taskRepository.deleteById(id);
    }

    @DeleteMapping("/tasks")
    public void removeAllTasks(){
        taskRepository.deleteAll();
    }

}
