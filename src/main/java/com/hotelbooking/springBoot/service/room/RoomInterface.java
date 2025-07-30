package com.hotelbooking.springBoot.service.room;

import com.hotelbooking.springBoot.dto.RoomDto;
import com.hotelbooking.springBoot.dto.RoomImageDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface RoomInterface {
    RoomDto add(List<MultipartFile> file,RoomDto roomDto) throws IOException;

    RoomDto updateById(String roomId, RoomDto roomDto, List<MultipartFile> file) throws IOException;
    List<RoomDto> getAll();
    void deleteById(String roomId);
    RoomDto getById(String roomId);
    List<String> getImageUrlsByRoomId(String roomId);
    Resource getImageUrl(String roomId, String imageId) throws MalformedURLException;
}
