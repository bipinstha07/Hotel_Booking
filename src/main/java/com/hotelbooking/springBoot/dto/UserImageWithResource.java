package com.hotelbooking.springBoot.dto;

import jakarta.annotation.Resource;

public record UserImageWithResource(UserImageDto userImageDto, Resource resource) {
}
