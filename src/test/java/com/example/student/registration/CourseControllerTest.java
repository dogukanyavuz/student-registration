package com.example.student.registration;

import com.example.student.registration.controller.CourseController;
import com.example.student.registration.controller.StudentController;
import com.example.student.registration.entity.Course;
import com.example.student.registration.entity.Student;
import com.example.student.registration.repository.CourseRepository;
import com.example.student.registration.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CourseController.class)
public class CourseControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    CourseRepository courseRepository;

    @MockBean
    CourseService courseService;

    @Test
    public void createCourse_success() throws Exception{
        Course course = Course.builder()
                .idCourse(1l)
                .courseName("java")
                .idStudent(1l)
                .build();

        Mockito.when(courseRepository.save(course)).thenReturn(course);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/course")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(course));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void getAll_success() throws Exception{
        Course course = Course.builder()
                .idCourse(1l)
                .courseName("java")
                .idStudent(1l)
                .build();

        Mockito.when(courseService.getCourses()).thenReturn(Collections.singletonList(course));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/course/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getCoursesByStudentId_success() throws Exception{
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

        Mockito.when(courseService.getCoursesByStudentId(student.getIdStudent())).thenReturn(Collections.singletonList(course));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/course/23")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
