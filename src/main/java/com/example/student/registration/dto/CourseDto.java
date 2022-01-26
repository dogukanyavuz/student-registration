package com.example.student.registration.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class CourseDto {

    @NotNull
    private long idStudent;

    @NotNull
    public String courseName;
}
