package com.services;

import com.models.Task;
import com.models.Users;
import com.repo.TaskRepository;
import com.repo.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    private TaskRepository taskRepository;
    private UsersRepository usersRepository;

    public DatabaseService (TaskRepository taskRepository, UsersRepository usersRepository){
        this.taskRepository = taskRepository;
        this.usersRepository = usersRepository;
    }

    public Users findUser(String email){
        return usersRepository.findByEmail(email);
    }

    public Page<Task> findAll(Pageable pageable){
        return taskRepository.findAll(pageable);
    }

    public Page<Task> findAll(Pageable pageable, Integer id){
        return taskRepository.findAllByUserId(pageable, id);
    }

    public void createNewUser(Users user){
        usersRepository.save(user);
    }

    public void createNewTask(Task task){
        taskRepository.save(task);
    }
}
