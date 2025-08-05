package com.hotelbooking.springBoot.service.user;

import com.hotelbooking.springBoot.dto.*;
import com.hotelbooking.springBoot.entity.User;
import com.hotelbooking.springBoot.entity.UserImage;
import com.hotelbooking.springBoot.exceptionHandling.ResourceNotFoundException;
import com.hotelbooking.springBoot.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.UrlResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserInterface {

    private final PasswordEncoder passwordEncoder;
    private UserRepo userRepo;
    private ModelMapper modelMapper;
    private UserImageInterface userImageInterface;

    @Override
    public UserDto create(UserDto userDto, MultipartFile image) throws IOException {
        userDto.setRole(RoleDto.CUSTOMER);
        userDto.setId(UUID.randomUUID().toString());
        userDto.setAccountCreation(LocalDateTime.now());
        UserImage userImage= userImageInterface.upload(image,userDto.getId());

        User user = modelMapper.map(userDto,User.class);
        user.setUserImage(userImage);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userImage.setUser(user);

        User savedUser = userRepo.save(user);
        UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);

        savedUserDto.setUserImage(userImage.getId());
        return savedUserDto;
    }


    @Override
    public UserDto getUserByUserName(String userName) {
        User user = userRepo.findByEmail(userName).orElseThrow(()->new ResourceNotFoundException("No user Found"));
        if (user == null) {
            throw new ResourceNotFoundException("No User Found");
        }
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserImageWithResource getUserWithImageById(String userEmail) throws MalformedURLException {

        User user = userRepo.findByEmail(userEmail).orElse(null);
        if (user == null) throw new ResourceNotFoundException("User not found");
        UserImage userImage = user.getUserImage();

        Path path;

        if(!userImage.isActualImage()){
            path = Path.of("uploads/userImage/1745900042295.jpeg");
        }

        else{
            path = Paths.get(userImage.getFileName());
        }


        if(!Files.exists(path)){
            throw new ResourceNotFoundException("No Path found for image");
        }

        UrlResource urlResource =  new UrlResource(path.toUri());
        return new UserImageWithResource(modelMapper.map(userImage,UserImageDto.class), urlResource);

        }


        @Override
        public UserDto updateUserById(String userName, UserDto userDto, MultipartFile file) throws IOException {
       User user = userRepo.findByEmail(userName).orElseThrow(()->new ResourceNotFoundException("No User Found"));
        user.setName(userDto.getName());// hash if needed
        user.setNumber(userDto.getNumber());
        user.setAddress(userDto.getAddress());
        user.setDateOfBirth(userDto.getDateOfBirth());
        if (file != null && !file.isEmpty()) {
            UserImage userImage  = userImageInterface.upload(file,user.getId()); // implement this
            user.setUserImage(userImage);// if you have such field
            userImage.setUser(user);
        }

        userRepo.save(user);
        return modelMapper.map(user,UserDto.class);
    }

}
