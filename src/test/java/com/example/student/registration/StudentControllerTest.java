package com.example.student.registration;

import com.example.student.registration.controller.StudentController;
import com.example.student.registration.dto.StudentDto;
import com.example.student.registration.entity.Course;
import com.example.student.registration.entity.Student;
import com.example.student.registration.repository.StudentRepository;
import com.example.student.registration.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers =StudentController.class)
public class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    StudentService studentService;

    @MockBean
    StudentRepository studentRepository;

    @Test
    public void createStudent_success() throws Exception {
        Student student1 = Student.builder()
                        .idStudent(2l).name("dogu").surname("yvz").build();

        Mockito.when(studentRepository.save(student1)).thenReturn(student1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/student")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(student1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void getAll_success() throws Exception{
        Student student = Student.builder()
                .idStudent(22l)
                .name("ali")
                .surname("duru")
                .build();

        Mockito.when(studentService.getStudents()).thenReturn(Collections.singletonList(student));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/student/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getStudentByCourseName_success() throws Exception{
        Student student = Student.builder()
                .idStudent(23l)
                .name("kayacan")
                .surname("kaya")
                .build();

        Course course = Course.builder()
                .idStudent(23l)
                .courseName("java")
                .idCourse(1l)
                .build();

        Mockito.when(studentService.getStudentsByCourseName("java")).thenReturn(Collections.singletonList(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/java")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
