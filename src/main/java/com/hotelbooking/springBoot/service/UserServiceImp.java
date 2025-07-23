package com.hotelbooking.springBoot.service;

import com.hotelbooking.springBoot.dto.RoleDto;
import com.hotelbooking.springBoot.dto.UserDto;
import com.hotelbooking.springBoot.entity.User;
import com.hotelbooking.springBoot.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserInterface{

    private UserRepo userRepo;
    private ModelMapper modelMapper;
    private UserImageInterface userImageInterface;

    @Override
    public UserDto create(UserDto userDto, MultipartFile image) throws IOException {
        userDto.setRole(RoleDto.CUSTOMER);
        userDto.setId(UUID.randomUUID().toString());
        userImageInterface.upload(image,userDto.getId());

        User user = modelMapper.map(userDto,User.class);
        User savedUser = userRepo.save(user);


        return modelMapper.map(savedUser,UserDto.class);
    }
}
