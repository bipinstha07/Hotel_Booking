package com.hotelbooking.springBoot.service.room;

import com.hotelbooking.springBoot.dto.RoomDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RoomInterface {
    RoomDto add(List<MultipartFile> file,RoomDto roomDto) throws IOException;
    List<RoomDto> getAll();
    RoomDto updateById(String roomId,RoomDto roomDto);
    void delete(String roomId);
    RoomDto getById(String roomId);
}
