package com.example.student.registration.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class StudentDto {

    @NotNull
    public String firstName;

    @NotNull
    public String lastName;
}
