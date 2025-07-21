package com.hotelbooking.springBoot.service;

import com.hotelbooking.springBoot.dto.RoleDto;
import com.hotelbooking.springBoot.dto.UserDto;
import com.hotelbooking.springBoot.entity.User;
import com.hotelbooking.springBoot.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserInterface{

    private UserRepo userRepo;
    private ModelMapper modelMapper;

    @Override
    public UserDto create(UserDto userDto) {
        userDto.setRole(RoleDto.CUSTOMER);

        User user = modelMapper.map(userDto,User.class);
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }
}
