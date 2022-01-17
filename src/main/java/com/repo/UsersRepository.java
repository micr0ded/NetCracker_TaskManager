package com.repo;

import com.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    Users findByUserId(int id);
    Users findByEmail(String email);
}