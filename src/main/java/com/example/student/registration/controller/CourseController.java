package com.example.student.registration.controller;

import com.example.student.registration.dto.CourseDto;
import com.example.student.registration.entity.Course;
import com.example.student.registration.entity.Student;
import com.example.student.registration.service.CourseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/course",
        produces= MediaType.APPLICATION_JSON_VALUE,
        consumes=MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    private @ResponseBody
    Course createUser(@RequestBody CourseDto courseDto) {
        return courseService.createCourse(courseDto);
    }

    @GetMapping("/{studentId}")
    private @ResponseBody
    List<Course> getCoursesByStudentId(@PathVariable Long studentId)
    {
        return this.courseService.getCoursesByStudentId(studentId);
    }

    @GetMapping("/getAll")
    private List<Course> getAllCourses()
    {
        return courseService.getCourses();
    }
}
