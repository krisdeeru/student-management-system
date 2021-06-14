package com.company.application.controllers;

import com.company.application.entities.Student;
import com.company.application.services.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {
    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void testListStudents() {
        final Model model = new ExtendedModelMap();
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
        final List<Student> students = Arrays.asList(firstStudent,secondStudent);
        when(studentService.getStudents()).thenReturn(students);

        final String result = studentController.listStudents(model);
        assertThat(result).isEqualTo("list-students");
    }

    @Test
    public void testListStudentsNoItems() {
        final Model model = new ExtendedModelMap();
        when(studentService.getStudents()).thenReturn(Collections.emptyList());
        final String result = studentController.listStudents(model);
        assertThat(result).isEqualTo("list-students");
    }

    @Test
    public void testShowFormForAdd() {
        final Model model = new ExtendedModelMap();
        final String result = studentController.showFormForAdd(model);
        assertThat(result).isEqualTo("student-form");
    }

    @Test
    public void testSaveStudent() {
        final Student student = new Student();
        student.setId(0L);
        student.setFirstName("firstName");
        student.setLastName("lastName");
        student.setEmail("email");

        final String result = studentController.saveStudent(student);
        assertThat(result).isEqualTo("redirect:/student/list");
        verify(studentService).saveStudent(any(Student.class));
    }

    @Test
    public void testShowFormForUpdate() {
        final Model model = new ExtendedModelMap();
        final Student student = new Student();
        student.setId(0L);
        student.setFirstName("firstName");
        student.setLastName("lastName");
        student.setEmail("email");
        when(studentService.getStudent(0L)).thenReturn(student);

        final String result = studentController.showFormForUpdate(0L, model);
        assertThat(result).isEqualTo("student-form");
    }

    @Test
    public void testDeleteStudent() {
        final String result = studentController.deleteStudent(0L);
        assertThat(result).isEqualTo("redirect:/student/list");
        verify(studentService).deleteStudent(0L);
    }
}
