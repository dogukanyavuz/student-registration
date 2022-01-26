package com.example.student.registration.service;

import com.example.student.registration.dto.CourseDto;
import com.example.student.registration.entity.Course;

import java.util.List;

public interface CourseService {

    Course createCourse(CourseDto courseDto);

    List<Course> getCoursesByStudentId(Long studentId);

    List<Course> getCourses();
}
