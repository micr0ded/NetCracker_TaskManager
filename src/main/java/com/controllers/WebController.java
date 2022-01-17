package com.controllers;

import com.Application;
import com.models.Task;
import com.models.Users;
import com.repo.TaskRepository;
import com.repo.UsersRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class WebController {
    private final TaskRepository taskRepository;
    private UsersRepository usersRepository;
    private Users currentUser;

    public WebController(TaskRepository repository, UsersRepository usersRepository){
        this.taskRepository = repository;
        this.usersRepository = usersRepository;
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
        Task task = new Task(description, time, currentUser.getUserId());
        taskRepository.save(task);
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String signIn(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model) {
        Users user = usersRepository.findByEmail(email);
        if (user.getPassword().equals(password)){
            currentUser = user;
        }
        return "redirect:/home";
    }



}