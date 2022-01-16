package com.service;

import com.models.Task;
import com.models.TaskDelivery;
import com.models.User;
import com.repo.TaskDeliveryRepository;
import com.repo.TaskRepository;
import com.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@AllArgsConstructor
public class NotificationScheduler {
    private TaskRepository taskRepository;
    private TaskDeliveryRepository taskDeliveryRepository;
    private UserRepository userRepository;
    private EmailSenderService senderService;

    @Scheduled(fixedDelay = 10000)
    public void run() {
        Iterable<Task> taskList = taskRepository.findTaskByTimeBeforeAndIsSentIsFalse(new Date());
        for(Task task: taskList){
            TaskDelivery delivery = taskDeliveryRepository.findByUserId(task.getUserId());
            switch (delivery.getDeliveryTypes()){
                case EMAIL:
                    User user = userRepository.findByUserId(task.getUserId());
                    //senderService.sendEmail(user.getEmail(), "test", task.getDescription());
                    //taskRepository.updateFlag(true, task.getID());
                    break;
                case CONSOLE:
                    //System.out.println("Message to user №" + task.getUserId() + " : " + task.getDescription());
                    //taskRepository.updateFlag(true, task.getID());
            }
        }
    }
}
