//package com;
//
//import com.models.Task;
//import com.models.TaskDelivery;
//import com.models.Users;
//import com.repo.TaskDeliveryRepository;
//import com.repo.TaskRepository;
//import com.repo.UsersRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//
//@Component
//@AllArgsConstructor
//public class NotificationScheduler {
//    private TaskRepository taskRepository;
//    private TaskDeliveryRepository taskDeliveryRepository;
//    private UsersRepository usersRepository;
//    private EmailSenderService senderService;
//
//    @Scheduled(fixedDelay = 10000)
//    public void run() {
//        Iterable<Task> taskList = taskRepository.findTaskByTimeBefore(new Date());
//        for(Task task: taskList){
//            TaskDelivery delivery = taskDeliveryRepository.findByUserId(task.getUserId());
//            switch (delivery.getDeliveryTypes()){
//                case Email:
//                    Users user = usersRepository.searchByUserId(task.getUserId());
//                    senderService.sendEmail(user.getEmail(), "test", task.getName());
//                    taskRepository.updateFlag(true, task.getID());
//                    break;
//                case ToConsole:
//                    System.out.println("Message to user №" + task.getUserId() + " : " + task.getName());
//                    taskRepository.updateFlag(true, task.getID());
//            }
//        }
//    }
//}
