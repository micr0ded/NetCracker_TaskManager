package com.repo;

import com.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserId(int id);
    User findByEmail(String email);
}