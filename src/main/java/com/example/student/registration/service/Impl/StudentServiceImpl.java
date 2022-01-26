package com.example.student.registration.service.Impl;

import com.example.student.registration.dto.StudentDto;
import com.example.student.registration.entity.Course;
import com.example.student.registration.entity.Student;
import com.example.student.registration.repository.CourseRepository;
import com.example.student.registration.repository.StudentRepository;
import com.example.student.registration.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Student createStudent(StudentDto studentDto) {
        Student student = Student.builder()
                .name(studentDto.getFirstName())
                .surname(studentDto.getLastName())
                .build();
        return this.studentRepository.save(student);
    }

    @Override public List<Student> getStudentsByCourseName(String courseName) {
        List<Course> courses = courseRepository.findByCourseNameEqualsIgnoreCase(courseName);
        List<Student> students = new ArrayList<>();
        for (Course course: courses) {
            Student student = studentRepository.findById(course.getIdStudent()).get();
            students.add(student);
        }
        return students;
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
}
