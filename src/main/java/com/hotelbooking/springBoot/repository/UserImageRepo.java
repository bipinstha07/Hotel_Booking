package com.hotelbooking.springBoot.repository;

import com.hotelbooking.springBoot.entity.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImageRepo extends JpaRepository<UserImage,String> {
}
