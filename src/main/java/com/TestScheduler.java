package com;

import com.models.Task;
import com.repo.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class TestScheduler {
    private TaskRepository taskRepository;
    private EmailSenderService senderService;

    public TestScheduler(TaskRepository repository, EmailSenderService mailSender){
        taskRepository = repository;
        senderService = mailSender;
    }

    @Scheduled(fixedDelay = 10000)
    public void run() {
        Iterable<Task> taskList = taskRepository.findTaskByTimeBefore(new Date());
        for(Task task: taskList){
           // senderService.sendEmail(task.getEmail(), "test", task.getDescription()); Пока афк
        }
    }
}
