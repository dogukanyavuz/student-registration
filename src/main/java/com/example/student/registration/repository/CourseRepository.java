package com.example.student.registration.repository;

import com.example.student.registration.entity.Course;
import com.example.student.registration.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {

    List<Course> findByCourseNameEqualsIgnoreCase(String courseName);

    long countByCourseNameContainsIgnoreCase(String courseName);

    List<Course> findByIdStudentEquals(Long idStudent);
}
