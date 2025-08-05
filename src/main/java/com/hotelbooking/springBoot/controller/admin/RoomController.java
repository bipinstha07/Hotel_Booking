package com.hotelbooking.springBoot.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbooking.springBoot.dto.BookingDto;
import com.hotelbooking.springBoot.dto.RoomDto;
import com.hotelbooking.springBoot.service.booking.BookingInterface;
import com.hotelbooking.springBoot.service.room.RoomInterface;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/room")
@AllArgsConstructor
public class RoomController {

    private RoomInterface roomInterface;
    private Validator validator;
    private BookingInterface bookingInterface;

    private transient final Logger logger = LoggerFactory.getLogger(RoomController.class);

    //  Post Mapping  --------------------------------------->In Use-----------------------------------------------------------------------------------------------
    //  Room Create
    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RoomDto> createBooking(@RequestPart  String roomData,  @RequestPart(value = "image", required = false) List<MultipartFile> image) throws IOException {

        RoomDto roomDto = new ObjectMapper().readValue(roomData, RoomDto.class);
        Set<ConstraintViolation<RoomDto>> violations = validator.validate(roomDto);
        if (!violations.isEmpty()) {
            logger.error("Validation Error while creating Room");
            throw new ConstraintViolationException(violations);
        }
        RoomDto roomDto1 = roomInterface.add(image,roomDto);
        return  new ResponseEntity<>(roomDto1, HttpStatus.CREATED);
    }



    //    Get Mapping  --------------------------------------->In Use-----------------------------------------------------------------------------------------------
    //    Get Room details by RoomId
    @GetMapping("/getById/{roomId}")
    public ResponseEntity<RoomDto> getById(@PathVariable String roomId){
        return new ResponseEntity<>(roomInterface.getById(roomId),HttpStatus.OK);
    }


    //    Update Mapping  --------------------------------------->In Use-----------------------------------------------------------------------------------------------
    @PostMapping(value = "/update/{roomId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RoomDto> updateById(@PathVariable String roomId ,@RequestPart  String roomData,  @RequestPart(value = "image", required = false) List<MultipartFile> image) throws IOException {
        RoomDto roomDto = new ObjectMapper().readValue(roomData, RoomDto.class);
        Set<ConstraintViolation<RoomDto>> violations = validator.validate(roomDto);
        if (!violations.isEmpty()) {
            logger.error("Validation Error while Updating the Room");
            throw new ConstraintViolationException(violations);
        }
        return new ResponseEntity<>(roomInterface.updateById(roomId,roomDto,image),HttpStatus.CREATED);
    }




    //     --------------------------------------->In Use-----------------------------------------------------------------------------------------------
    //    Get Imaegs By room Id
    @GetMapping("/{roomId}/images")
    public ResponseEntity<List<String>> getRoomImages(@PathVariable String roomId) {
        List<String> imageUrls = roomInterface.getImageUrlsByRoomId(roomId);
        return ResponseEntity.ok(imageUrls);
    }

    //    Delete Mapping  --------------------------------------->In Use-----------------------------------------------------------------------------------------------
    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable String roomId){
        roomInterface.deleteById(roomId);
        return new ResponseEntity<>("Deletion Success",HttpStatus.OK);
    }

    //    Get Mapping  --------------------------------------->In Use-----------------------------------------------------------------------------------------------
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

    //    Get Mapping  --------------------------------------->In Use-----------------------------------------------------------------------------------------------
    @GetMapping("/getAll")
    public ResponseEntity<List<RoomDto>> getAll(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getAuthorities().forEach(a -> System.out.println("ROLE: " + a.getAuthority()));
        return new ResponseEntity<>(roomInterface.getAll(),HttpStatus.OK);
    }


    @PostMapping("/deleteById/{roomId}")
    public ResponseEntity<String> deleteById(@PathVariable String roomId){
        roomInterface.deleteById(roomId);
        return new ResponseEntity<>("Deletion Success",HttpStatus.OK);
    }




//    Booking --------------------->In use-------------------------------------------------------------------------------------
    @GetMapping("/booking/getAll")
    public ResponseEntity<List<BookingDto>> getAllBooking(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getAuthorities().forEach(a -> System.out.println("ROLE: " + a.getAuthority()));
        System.out.println("I am getting all");
        return new ResponseEntity<>(bookingInterface.getAll(),HttpStatus.OK);
    }

//    Patch Mapping  --------------------------------------->In Use-----------------------------------------------------------------------------------------------
    @PatchMapping("/booking/update/{roomId}")
    public ResponseEntity<String> updateBookingStatus(@PathVariable String roomId, String bookingStatus){
        logger.info("Update Booking",roomId,bookingStatus);
        bookingInterface.updateBookingStatus(roomId,bookingStatus);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    @GetMapping("/booking/totalRevenue")
    public ResponseEntity<Integer> totalRevenue(){
        return new ResponseEntity<>(bookingInterface.getTotalRevenue(),HttpStatus.OK);
    }

}
