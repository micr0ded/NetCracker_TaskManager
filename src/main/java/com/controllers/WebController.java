package com.controllers;

import com.annotations.UserId;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.models.Task;
import com.models.Users;
import com.services.DatabaseService;
import com.services.JWTAlgoService;
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
    private Integer tmpID;
    private DatabaseService databaseService;

    public WebController(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    @GetMapping("/")
    public String defaultPage(Model model){
        return "login";
    }

    @GetMapping("/home")
    public String home(
            @UserId Integer userId,
            Model model,
            @PageableDefault(sort = {"ID"}, direction = Sort.Direction.ASC, size = 8) Pageable pageable
    ) {
        Page<Task> page;
        page = databaseService.findAll(pageable, userId);
        model.addAttribute("page", page);
        model.addAttribute("url", "/home");
        tmpID = userId;
        return "home";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("localTime", LocalTime.now());
        return "add-task";
    }

    @PostMapping("/add")
    public String addTask(@UserId Integer userId, @RequestParam String date, @RequestParam String time, @RequestParam String description, Model model) throws ParseException {

//        System.out.println(date);
//        System.out.println(time);
        date = date + ' ' + time;
        System.out.println(date);
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm");
        Task task = new Task(description, (Date)formatter.parse(date), userId);
        databaseService.createNewTask(task);
        String token = JWTAlgoService.createToken(userId);
        return "redirect:/home?token=" + token;
    }

    @GetMapping("/login")
    public String signIn(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model) {
        if (databaseService.findUser(email).getPassword().equals(password)) {
            try {
                Integer id = databaseService.findUser(email).getUserId();
                String token = JWTAlgoService.createToken(id);
                return "redirect:/home?token=" + token;
            } catch (JWTCreationException exception) {
                System.out.println("Error when creating login token");
            }
        }
        return "redirect:/login";
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
            databaseService.createNewUser(newUser);
            Integer id = databaseService.findUser(email).getUserId();
            String token = JWTAlgoService.createToken(id);
            return "redirect:/home" + token;
        }
        return "redirect:/home";
    }
}
