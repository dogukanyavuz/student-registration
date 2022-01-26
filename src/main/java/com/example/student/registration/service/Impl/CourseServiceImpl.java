package com.example.student.registration.service.Impl;

import com.example.student.registration.dto.CourseDto;
import com.example.student.registration.entity.Course;
import com.example.student.registration.entity.Student;
import com.example.student.registration.exception.CourseSizeIsFullException;
import com.example.student.registration.repository.CourseRepository;
import com.example.student.registration.repository.StudentRepository;
import com.example.student.registration.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public Long controlCourseCount (String courseName)
    {
        return courseRepository.countByCourseNameContainsIgnoreCase(courseName);
    }

    @Override
    public Course createCourse(CourseDto courseDto) {
        if (controlCourseCount(courseDto.getCourseName())<50)
        {
            Course course = Course.builder()
                    .courseName(courseDto.getCourseName())
                    .idStudent(courseDto.getIdStudent())
                    .build();

            return this.courseRepository.save(course);
        }
            throw new CourseSizeIsFullException("Course size is full");
    }

    @Override
    public List<Course> getCoursesByStudentId(Long studentId) {
        List<Course> courses = courseRepository.findByIdStudentEquals(studentId);
        List<Course> courses1 = new ArrayList<>();
        for (Course course: courses) {
            courses1.add(course);
        }
        return courses1;
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }
}
