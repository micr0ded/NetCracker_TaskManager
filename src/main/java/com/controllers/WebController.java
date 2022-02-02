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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class WebController {
    private TaskRepository taskRepository;
    public static Users currentUser;
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

        if (currentUser.getUserId() == null){
            page = taskRepository.findAll(pageable);
        }
        page = taskRepository.findAllByUserId(pageable, currentUser.getUserId());

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

    @GetMapping("/registry")
    public String registry(Model model){
        return "registry";
    }

    @PostMapping("/registry")
    public String registry(@RequestParam String email, @RequestParam String password, Model model) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()){
            Users newUser = new Users(email, password);
            usersRepository.save(newUser);
        }
        return "redirect:/home";
    }
}
