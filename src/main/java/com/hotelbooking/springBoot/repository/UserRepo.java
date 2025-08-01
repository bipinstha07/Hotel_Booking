package com.hotelbooking.springBoot.repository;

import com.hotelbooking.springBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    User findUserById(String id);

    Optional<User> findByEmail(String email);
}
