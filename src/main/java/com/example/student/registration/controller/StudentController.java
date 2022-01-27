package com.example.student.registration.controller;

import com.example.student.registration.dto.CourseDto;
import com.example.student.registration.dto.StudentDto;
import com.example.student.registration.entity.Course;
import com.example.student.registration.entity.Student;
import com.example.student.registration.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(
        value = "/student",
        produces= MediaType.APPLICATION_JSON_VALUE,
        consumes=MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    private @ResponseBody
    Student createStudent(@RequestBody @Valid StudentDto studentDto) {
        return studentService.createStudent(studentDto);
    }

    @GetMapping("/{courseName}")
    private @ResponseBody
    List<Student> getStudentByCourseName(@PathVariable String courseName)
    {
        return this.studentService.getStudentsByCourseName(courseName);
    }

    @GetMapping("/getAll")
    private List<Student> getAllStudents()
    {
        return this.studentService.getStudents();
    }
}
