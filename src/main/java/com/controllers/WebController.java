package com.controllers;

import com.models.Task;
import com.repo.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class WebController {
    private TaskRepository taskRepository;

    public WebController(TaskRepository repository){
        this.taskRepository = repository;
    }

    @GetMapping("/home")
    public String home(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam (value = "size", defaultValue = "10") int size, Model model){
        model.addAttribute("tasks", taskRepository.findAll(PageRequest.of(page, size)));
        return "home";
    }
    @GetMapping("/home/{page}")
    public String getPage(@PathVariable int page, @RequestParam (value = "size", defaultValue = "10") int size, Model model){
        model.addAttribute("tasks", taskRepository.findAll(PageRequest.of(page, size)));
        model.addAttribute("page", page);
        return "home";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "add-task";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam Date time, @RequestParam String description, Model model) {
        Task task = new Task(description, time);
        taskRepository.save(task);
        return "redirect:/home";
    }
}
