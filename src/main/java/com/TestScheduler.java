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

    public TestScheduler(TaskRepository repository){
        taskRepository = repository;
    }

    @Scheduled(fixedDelay = 10000)
    public void run() {
        tmp = taskRepository.findAll();
    }
}
