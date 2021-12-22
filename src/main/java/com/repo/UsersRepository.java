package com.repo;

import com.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    Users searchByUserId(int id);
}