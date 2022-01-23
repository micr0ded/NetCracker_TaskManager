package com.controllers;

import com.models.Task;
import com.models.Users;
import com.repo.TaskRepository;
import com.repo.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Controller
public class WebController {
    private TaskRepository taskRepository;
    private Users currentUser;
    private UsersRepository usersRepository;
    public WebController(TaskRepository repository, UsersRepository usersRepository){
        this.taskRepository = repository;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/")
    public String defPage(Model model){
        return "login";
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
        Task task = new Task(description, (Date)formatter.parse(date), currentUser.getUserId());

        taskRepository.save(task);
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String signIn(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model) {
        Users user = new Users(email, password);
        currentUser = user;
        return "redirect:/home";
    }

}
