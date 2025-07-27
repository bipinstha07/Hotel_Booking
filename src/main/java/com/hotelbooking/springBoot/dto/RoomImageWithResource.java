package com.hotelbooking.springBoot.dto;

import org.springframework.core.io.Resource;

public record RoomImageWithResource(RoomImageDto roomImageDto, Resource resource) {
}
