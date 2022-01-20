package com.controllers;

import com.models.Task;
import com.repo.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

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
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("localTime", LocalTime.now());
        return "add-task";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String date, @RequestParam String time, @RequestParam String description, Model model) throws ParseException {

//        System.out.println(date);
//        System.out.println(time);
        date = date + ' ' + time;
        System.out.println(date);
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm");
        Task task = new Task(description, (Date)formatter.parse(date), 1);

        taskRepository.save(task);
        return "redirect:/home";
    }

}
