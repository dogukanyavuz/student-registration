package com.example.student.registration.exception;

public class CourseSizeIsFullException extends RuntimeException {

    public CourseSizeIsFullException (String message)
    {
        super(message);
    }
}
