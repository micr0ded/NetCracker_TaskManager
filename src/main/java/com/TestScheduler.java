package com;

import com.models.Task;
import com.repo.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.Clock;

@Component
public class TestScheduler {
    private TaskRepository taskRepository;
    private  Iterable<Task> tmp;
    private EmailSenderService senderService;
    private String mailString;

    public TestScheduler(TaskRepository repository, EmailSenderService mailSender){
        taskRepository = repository;
        this.senderService = mailSender;
    }

    @Scheduled(fixedDelay = 10000)
    public void run() {
        tmp = taskRepository.findAll();
        for(Task task: tmp){
            mailString = task.getEmail();
            senderService.sendEmail(mailString, "Subject", task.getDescription());
        }
    }
}
