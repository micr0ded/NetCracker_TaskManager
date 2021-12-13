package com;

import com.repo.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {
    private TaskRepository taskRepository;

    public TestScheduler(TaskRepository repository){
        taskRepository = repository;
    }

    @Scheduled(fixedDelay = 10000)
    public void run(){
        System.out.println("test");
    }
}
