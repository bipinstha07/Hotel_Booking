package com.hotelbooking.springBoot.service;

import com.hotelbooking.springBoot.dto.RoomDto;
import com.hotelbooking.springBoot.entity.Room;
import com.hotelbooking.springBoot.entity.RoomType;
import com.hotelbooking.springBoot.exceptionHandling.ResourceNotFoundException;
import com.hotelbooking.springBoot.repository.RoomRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class RoomServiceImp implements RoomInterface{

    private ModelMapper modelMapper;
    private RoomRepo roomRepo;

    @Override
    public RoomDto add(RoomDto roomDto){
        Room roomEntity = modelMapper.map(roomDto,Room.class);
        Room savedRoomEntity  = roomRepo.save(roomEntity);
        return  modelMapper.map(savedRoomEntity,RoomDto.class);

    }

    @Override
    public List<RoomDto> getAll() {
        List<Room> allRoom = roomRepo.findAll();
        List<RoomDto> allRoomDto = allRoom.stream().map((room)-> modelMapper.map(room,RoomDto.class)).toList();
        return allRoomDto;
    }



    @Override
    public RoomDto updateById(String roomId,RoomDto roomDto) {
        Room room = roomRepo.findById(roomId).orElseThrow(()->new ResourceNotFoundException("No Room Found"));
       room.setCapacity(roomDto.getCapacity());
       room.setPricePerNight(roomDto.getPricePerNight());
       RoomType r = RoomType.valueOf(roomDto.getRoomType());
       room.setRoomType(r);
       return modelMapper.map(roomRepo.save(room),RoomDto.class);
    }

    @Override
    public void delete(String roomId) {
        Room room = roomRepo.findById(roomId).orElseThrow(()-> new ResourceNotFoundException("No Room Found"));
        roomRepo.delete(room);
    }

    @Override
    public RoomDto getById(String roomId) {
        Room room = roomRepo.findById(roomId).orElseThrow(()-> new ResourceNotFoundException("No Room Found"));
        return modelMapper.map(room,RoomDto.class);
    }


}
