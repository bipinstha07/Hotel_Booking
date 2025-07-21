package com.hotelbooking.springBoot.dto;

public record ErrorResponseDto(String message,int code, boolean isSuccess) {
}
