package com.hotelbooking.springBoot.service.room;

import com.hotelbooking.springBoot.dto.RoomDto;
import com.hotelbooking.springBoot.entity.Room;
import com.hotelbooking.springBoot.entity.RoomImage;
import com.hotelbooking.springBoot.entity.RoomType;
import com.hotelbooking.springBoot.exceptionHandling.ResourceNotFoundException;
import com.hotelbooking.springBoot.repository.RoomImageRepo;
import com.hotelbooking.springBoot.repository.RoomRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Getter
@Setter
@AllArgsConstructor
public class RoomServiceImp implements RoomInterface {

    private final RoomImageRepo roomImageRepo;
    private ModelMapper modelMapper;
    private RoomRepo roomRepo;
    private RoomImageInterface roomImageInterface;
    private RoomImageRepo imageRepo;
    private transient final Logger logger = LoggerFactory.getLogger(RoomServiceImp.class);


    @Override
    public RoomDto add(List<MultipartFile> file,RoomDto roomDto) throws IOException {
        roomDto.setId(UUID.randomUUID().toString());
        Room roomEntity = modelMapper.map(roomDto,Room.class);

       List<RoomImage> roomImage = roomImageInterface.upload(file,roomDto.getId());

        roomEntity.setRoomImage(roomImage);
        for(RoomImage roomImage1: roomImage){
            roomImage1.setRoom(roomEntity);
        }
        Room savedRoomEntity  = roomRepo.save(roomEntity);
        logger.info("New Room Created with Id {} and Room Number {}",savedRoomEntity.getId(),roomEntity.getRoomNumber());
        RoomDto roomDto1 = modelMapper.map(savedRoomEntity,RoomDto.class);
        List<String> ids = new ArrayList<>();
       for (int i =0; i<roomImage.size();i++){
           ids.add(roomImage.get(i).getId());
       }
        roomDto1.setRoomImageIds(ids);
        return  roomDto1;

    }

    @Override
    public RoomDto updateById(String roomId, RoomDto roomDto, List<MultipartFile> file) throws IOException {
        Room room = roomRepo.findById(roomId).orElseThrow(()->new ResourceNotFoundException("No Room Found"));
        room.setCapacity(roomDto.getCapacity());
        room.setPricePerNight(roomDto.getPricePerNight());
        room.setAmenities(roomDto.getAmenities());
        room.setDescription(roomDto.getDescription());
        room.setRoomNumber(roomDto.getRoomNumber());

        List<String> ids = new ArrayList<>();
        if(file != null){
            List<RoomImage> roomImage = roomImageInterface.upload(file,roomDto.getId());
            room.setRoomImage(roomImage);
            for(RoomImage roomImage1: roomImage){
                roomImage1.setRoom(room);
            }

            for (int i =0; i<roomImage.size();i++){
                ids.add(roomImage.get(i).getId());
            }
        }

        RoomType r = RoomType.valueOf(roomDto.getRoomType());
        room.setRoomType(r);
        RoomDto roomDto1 = modelMapper.map(roomRepo.save(room),RoomDto.class);
        logger.info("Room Updated with Id {} and Room Number {}",roomDto1.getId(),roomDto1.getRoomNumber());

        if(file != null){
            roomDto1.setRoomImageIds(ids);
        }

        return  roomDto1;
    }

    @Override
    public void deleteById(String roomId) {
        Room room = roomRepo.findById(roomId).orElseThrow(()-> new ResourceNotFoundException("No Room Found"));
        roomRepo.delete(room);
        logger.info("Room Deleted with Id {} and Room Number {}",room.getId(),room.getRoomNumber());
    }

    @Override
    public List<RoomDto> getAll() {
        List<Room> allRoom = roomRepo.findAll();
        List<RoomDto> allRoomDto = allRoom.stream().map((room)-> modelMapper.map(room,RoomDto.class)).toList();
        return allRoomDto;
    }


    public List<String> getImageUrlsByRoomId(String roomId){
       Room room= roomRepo.findById(roomId).orElseThrow(()->new ResourceNotFoundException("No Room Found"));
       List<String> imageId = new ArrayList<>();
       for(RoomImage roomImage:room.getRoomImage()){
           imageId.add(roomImage.getId());
       }
        return imageId;
    }

    @Override
    public Resource getImageUrl(String roomId, String imageId) throws MalformedURLException {
        Room room= roomRepo.findById(roomId).orElseThrow(()->new ResourceNotFoundException("No Room Found"));
        RoomImage roomImage = roomImageRepo.findById(imageId).orElseThrow(()->new ResourceNotFoundException("No Image"));
        Path path = Paths.get(roomImage.getFileName());
       return  new UrlResource(path.toUri());
    }


    @Override
    public RoomDto getById(String roomId) {
        Room room = roomRepo.findById(roomId).orElseThrow(()-> new ResourceNotFoundException("No Room Found"));
        return modelMapper.map(room,RoomDto.class);
    }


}
