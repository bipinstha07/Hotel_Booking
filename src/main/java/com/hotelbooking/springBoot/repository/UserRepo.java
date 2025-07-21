package com.hotelbooking.springBoot.repository;

import com.hotelbooking.springBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
