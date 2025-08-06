package com.hotelbooking.springBoot.service.user;

import com.hotelbooking.springBoot.dto.*;
import com.hotelbooking.springBoot.entity.User;
import com.hotelbooking.springBoot.entity.UserImage;
import com.hotelbooking.springBoot.exceptionHandling.ResourceNotFoundException;
import com.hotelbooking.springBoot.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImp implements UserInterface {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final UserImageInterface userImageInterface;
    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    public UserServiceImp(UserRepo userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder, S3Client s3Client, UserImageInterface userImageInterface) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.s3Client = s3Client;
        this.userImageInterface = userImageInterface;
    }


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
    public UserImageWithResource getUserWithImageById(String userEmail) throws IOException {

        User user = userRepo.findByEmail(userEmail).orElse(null);
        if (user == null) throw new ResourceNotFoundException("User not found");
        UserImage userImage = user.getUserImage();

        Path path;

        if(!userImage.isActualImage()){
            path = Path.of("uploads/userImage/1745900042295.jpeg");
        }

        else{
//            Locally
//            path = Paths.get(userImage.getFileName());
            String key = userImage.getFileName(); // this should be the S3 key
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(getObjectRequest);

            Path tempFile = Files.createTempFile("s3-download-", ".tmp");
            Files.copy(s3Object, tempFile, StandardCopyOption.REPLACE_EXISTING);
            path = tempFile;
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
