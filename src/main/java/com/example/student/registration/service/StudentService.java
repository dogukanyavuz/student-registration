package com.example.student.registration.service;

import com.example.student.registration.dto.CourseDto;
import com.example.student.registration.dto.StudentDto;
import com.example.student.registration.entity.Course;
import com.example.student.registration.entity.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(StudentDto studentDto);

    List<Student> getStudentsByCourseName(String courseName);

    List<Student> getStudents();
}
