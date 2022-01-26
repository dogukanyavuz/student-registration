package com.example.student.registration.repository;

import com.example.student.registration.entity.Course;
import com.example.student.registration.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
