package com.hotelbooking.springBoot.service.user;

import com.hotelbooking.springBoot.dto.UserImageDto;
import com.hotelbooking.springBoot.entity.UserImage;
import com.hotelbooking.springBoot.repository.UserImageRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class UserImageImp implements UserImageInterface {

    @Value("${userImage.file.path}")
    private String imagePath;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    @Autowired
    private S3Client s3Client;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserImageRepo userImageRepo;

    private Logger logger = LoggerFactory.getLogger(UserImageImp.class);

    @Override
    public UserImage upload(MultipartFile file, String userId) throws IOException {

        if(!Files.exists(Paths.get(imagePath))){
            Files.createDirectories(Paths.get(imagePath));
        }

        if (file == null || file.isEmpty()) {
            // skip saving or set default image
            UserImage userImage = new UserImage();;
            userImage.setId(UUID.randomUUID().toString());
            userImage.setSize(null);
            userImage.setFileType("image/jpeg");
            userImage.setFileName("NoImage");
            userImage.setActualImage(false);
            return userImage;
        }

        else{
            String fullFileName = imagePath+ file.getOriginalFilename();
//            For locally
//            Files.copy(file.getInputStream(),Paths.get(fullFileName), StandardCopyOption.REPLACE_EXISTING);
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fullFileName)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
//AWS S3
            UserImageDto userImageDto = new UserImageDto();
            userImageDto.setId(UUID.randomUUID().toString());
            userImageDto.setFileName(fullFileName);
            userImageDto.setActualImage(true);
            userImageDto.setFileType(file.getContentType());
            userImageDto.setSize(file.getSize());
            UserImage userImage = modelMapper.map(userImageDto,UserImage.class);
            return  userImage;
        }

// proceed to Files.copy(...)


    }

}
