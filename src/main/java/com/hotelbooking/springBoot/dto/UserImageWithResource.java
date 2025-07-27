package com.hotelbooking.springBoot.dto;

import org.springframework.core.io.Resource;

public record UserImageWithResource(UserImageDto userImageDto, Resource resource) {
}
