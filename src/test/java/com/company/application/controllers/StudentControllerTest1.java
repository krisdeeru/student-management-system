package com.company.application.controllers;

import com.company.application.entities.Student;
import com.company.application.services.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application.properties")
public class StudentControllerTest1 {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void testListStudents() throws Exception {
        Student firstStudent = new Student();
        firstStudent.setId(1L);
        firstStudent.setFirstName("Krishna");
        firstStudent.setLastName("Karlapudi");
        firstStudent.setEmail("krish@gmail.com");

        Student secondStudent = new Student();
        secondStudent.setId(2L);
        secondStudent.setFirstName("Dheeraj");
        secondStudent.setLastName("Karlapudi");
        secondStudent.setEmail("deeru@gmail.com");
        when(studentService.getStudents()).thenReturn(Arrays.asList(firstStudent, secondStudent));

        mockMvc.perform(get("/student/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("list-students"))
                .andExpect(forwardedUrl("/list-students.jsp"))
                .andExpect(model().attribute("students", hasSize(2)))
                .andExpect(model().attribute("students", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("firstName", is("Krishna")),
                                hasProperty("lastName", is("Karlapudi")),
                                hasProperty("email", is("krish@gmail.com"))
                        )
                )))
                .andExpect(model().attribute("students", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("firstName", is("Dheeraj")),
                                hasProperty("lastName", is("Karlapudi")),
                                hasProperty("email", is("deeru@gmail.com"))
                        )
                )));
    }
}
