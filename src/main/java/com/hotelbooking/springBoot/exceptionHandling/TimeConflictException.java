package com.hotelbooking.springBoot.exceptionHandling;

public class TimeConflictException extends RuntimeException {
    public TimeConflictException(){super("Time Conflict");}
    public TimeConflictException(String message) {
        super(message);
    }
}
