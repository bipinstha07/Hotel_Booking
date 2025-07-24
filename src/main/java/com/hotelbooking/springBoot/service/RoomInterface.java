package com.hotelbooking.springBoot.service;

import com.hotelbooking.springBoot.dto.RoomDto;

import java.util.List;

public interface RoomInterface {
    RoomDto add(RoomDto roomDto);
    List<RoomDto> getAll();
    RoomDto updateById(String roomId,RoomDto roomDto);
}
