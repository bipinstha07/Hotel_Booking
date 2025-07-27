package com.hotelbooking.springBoot.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbooking.springBoot.dto.RoomDto;
import com.hotelbooking.springBoot.service.room.RoomInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/room")
@AllArgsConstructor
public class RoomController {

    private RoomInterface roomInterface;

    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RoomDto> createBooking(@RequestPart  String roomdata,  @RequestPart(value = "image", required = false) List<MultipartFile> image) throws IOException {
        RoomDto roomDto = new ObjectMapper().readValue(roomdata, RoomDto.class);
        RoomDto roomDto1 = roomInterface.add(image,roomDto);
        return  new ResponseEntity<>(roomDto1, HttpStatus.CREATED);
    }

    @PostMapping("/getById/{roomId}")
    public ResponseEntity<RoomDto> getById(@PathVariable String roomId){
        return new ResponseEntity<>(roomInterface.getById(roomId),HttpStatus.OK);
    }

    @PostMapping("/deleteById/{roomId}")
    public ResponseEntity<String> deleteById(@PathVariable String roomId){
        roomInterface.delete(roomId);
        return new ResponseEntity<>("Deletion Success",HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RoomDto>> getAll(){
        return new ResponseEntity<>(roomInterface.getAll(),HttpStatus.OK);
    }

    @PutMapping("/update/{roomId}")
    public ResponseEntity<RoomDto> updateById(@PathVariable String roomId,@RequestBody RoomDto roomDto){
        return new ResponseEntity<>(roomInterface.updateById(roomId,roomDto),HttpStatus.CREATED);
    }


}
