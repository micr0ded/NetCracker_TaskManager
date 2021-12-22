package com;

import com.models.Task;
import com.models.TaskDelivery;
import com.models.Users;
import com.repo.TaskDeliveryRepository;
import com.repo.TaskRepository;
import com.repo.UsersRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;


@Component
public class TestScheduler {
    private TaskRepository taskRepository;
    private TaskDeliveryRepository taskDeliveryRepository;
    private UsersRepository usersRepository;
    private EmailSenderService senderService;

    public TestScheduler(TaskRepository repository, TaskDeliveryRepository taskDeliveryRepository, UsersRepository usersRepository, EmailSenderService mailSender){
        this.taskRepository = repository;
        this.taskDeliveryRepository = taskDeliveryRepository;
        this.usersRepository = usersRepository;
        this.senderService = mailSender;

    }

    @Scheduled(fixedDelay = 10000)
    public void run() {
        Iterable<Task> taskList = taskRepository.findTaskByTimeBefore(new Date());
        for(Task task: taskList){
            TaskDelivery delivery = taskDeliveryRepository.findByUserId(task.getUserId());
            switch (delivery.getDeliveryTypes()){
                case Email:
                    Users user = usersRepository.searchByUserId(task.getUserId());
                   // senderService.sendEmail(user.getEmail(), "test", task.getDescription());
                    break;
                case ToConsole:
                    System.out.println("Message to user №" + task.getUserId() + " : " + task.getDescription());
            }
        }
    }
}
