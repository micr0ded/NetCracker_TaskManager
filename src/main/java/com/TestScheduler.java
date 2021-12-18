package com;

import com.models.Task;
import com.repo.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class TestScheduler {
    private TaskRepository taskRepository;
    private Iterable<Task> tmp;
    private EmailSenderService senderService;
    private String mailString;

    public TestScheduler(TaskRepository repository, EmailSenderService mailSender){
        taskRepository = repository;
        senderService = mailSender;
    }

   /* @Scheduled(fixedDelay = 10000)
    public void run() {
        senderService.sendEmail("marble462@gmail.com", "Subject", "Body");
    }*/
}
