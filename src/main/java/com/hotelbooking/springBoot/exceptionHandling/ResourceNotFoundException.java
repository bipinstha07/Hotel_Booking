package com.hotelbooking.springBoot.exceptionHandling;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(){
        super("Resource Not Found");
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
