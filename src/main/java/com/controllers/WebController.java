package com.controllers;

import com.models.Task;
import com.repo.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    private TaskRepository taskRepository;

    public WebController(TaskRepository repository){
        this.taskRepository = repository;
    }

    @GetMapping("/home")
    public String home(
            Model model,
            @PageableDefault(sort = {"ID"}, direction = Sort.Direction.ASC, size = 8) Pageable pageable
    ) {
        Page<Task> page;

        page = taskRepository.findAll(pageable);

        model.addAttribute("page", page);
        model.addAttribute("url", "/home");
        return "home";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "add-task";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam Long time, @RequestParam String description, Model model) {
        Task task = new Task(description, time);
        taskRepository.save(task);
        return "redirect:/home";
    }

}
