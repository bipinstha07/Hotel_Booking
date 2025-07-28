package com.hotelbooking.springBoot.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbooking.springBoot.dto.RoomDto;
import com.hotelbooking.springBoot.dto.RoomImageDto;
import com.hotelbooking.springBoot.service.room.RoomInterface;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/room")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class RoomController {

    private RoomInterface roomInterface;
    private Validator validator;

    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RoomDto> createBooking(@RequestPart  String roomData,  @RequestPart(value = "image", required = false) List<MultipartFile> image) throws IOException {
        RoomDto roomDto = new ObjectMapper().readValue(roomData, RoomDto.class);

        Set<ConstraintViolation<RoomDto>> violations = validator.validate(roomDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        RoomDto roomDto1 = roomInterface.add(image,roomDto);
        return  new ResponseEntity<>(roomDto1, HttpStatus.CREATED);
    }

//    Get Room details by RoomId
    @GetMapping("/getById/{roomId}")
    public ResponseEntity<RoomDto> getById(@PathVariable String roomId){
        return new ResponseEntity<>(roomInterface.getById(roomId),HttpStatus.OK);
    }

//    Get Imaegs By room Id
    @GetMapping("/{roomId}/images")
    public ResponseEntity<List<String>> getRoomImages(@PathVariable String roomId) {
        List<String> imageUrls = roomInterface.getImageUrlsByRoomId(roomId);
        return ResponseEntity.ok(imageUrls);
    }

    @GetMapping("/{roomId}/images/{imageId}")
    public ResponseEntity<Resource> getImagesById(@PathVariable String imageId, @PathVariable String roomId) throws IOException {
        Resource resource = roomInterface.getImageUrl(roomId, imageId);
        Path path = Paths.get(resource.getURI());
        String contentType = Files.probeContentType(path);

        if (contentType == null) contentType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @PostMapping("/deleteById/{roomId}")
    public ResponseEntity<String> deleteById(@PathVariable String roomId){
        roomInterface.deleteById(roomId);
        return new ResponseEntity<>("Deletion Success",HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RoomDto>> getAll(){
        return new ResponseEntity<>(roomInterface.getAll(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable String roomId){
        roomInterface.deleteById(roomId);
        return new ResponseEntity<>("Deletion Success",HttpStatus.OK);
    }

    @PutMapping("/update/{roomId}")
    public ResponseEntity<RoomDto> updateById(@PathVariable String roomId,@RequestBody RoomDto roomDto){
        return new ResponseEntity<>(roomInterface.updateById(roomId,roomDto),HttpStatus.CREATED);
    }


}
