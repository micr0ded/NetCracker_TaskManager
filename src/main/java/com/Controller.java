package com;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    private final Service service;

    public Controller(Service service){
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }


    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable(value = "id") int id) {
        return service.getTask(id);
    }

    @PostMapping("/tasks")
    public void addTask(@RequestBody Task task){
        service.addTask(task);
    }


    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return service.getAllTasks();
    }
}
