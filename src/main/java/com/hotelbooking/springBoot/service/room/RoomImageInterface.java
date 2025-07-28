package com.hotelbooking.springBoot.service.room;

import com.hotelbooking.springBoot.dto.RoomImageDto;
import com.hotelbooking.springBoot.dto.RoomImageWithResource;
import com.hotelbooking.springBoot.entity.RoomImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RoomImageInterface {
    List<RoomImage> upload(List<MultipartFile> file, String roomId) throws IOException;


    RoomImageWithResource loadImage(Long userId);

}
