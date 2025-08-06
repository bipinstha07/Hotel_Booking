package com.hotelbooking.springBoot.service.room;

import com.hotelbooking.springBoot.dto.RoomImageDto;
import com.hotelbooking.springBoot.dto.RoomImageWithResource;
import com.hotelbooking.springBoot.entity.RoomImage;
import com.hotelbooking.springBoot.repository.RoomImageRepo;
import org.modelmapper.ModelMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoomImageImp implements RoomImageInterface{

    @Value("${roomImage.file.path}")
    private String imagePath;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private S3Client s3Client;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    @Override
    public List<RoomImage> upload(List<MultipartFile> files, String roomId) throws IOException {
        if(!Files.exists(Paths.get(imagePath))){
            Files.createDirectories(Paths.get(imagePath));
        }

        List<RoomImage> allRoomImage = new ArrayList<>();

        for(MultipartFile file:files){
            String fullFileName = imagePath+ file.getOriginalFilename();
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fullFileName)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
//AWS S3

            Files.copy(file.getInputStream(),Paths.get(fullFileName), StandardCopyOption.REPLACE_EXISTING);
            RoomImageDto roomImageDto = new RoomImageDto();
            roomImageDto.setId(UUID.randomUUID().toString());
            roomImageDto.setFileName(fullFileName);
            roomImageDto.setFileType(file.getContentType());
            roomImageDto.setSize(file.getSize());
            RoomImage roomImage = modelMapper.map(roomImageDto,RoomImage.class);
            allRoomImage.add(roomImage);
        }

        return allRoomImage;
    }




    @Override
    public RoomImageWithResource loadImage(Long userId) {
        return null;
    }
}
