package com.company.application.services;

import com.company.application.entities.Student;
import com.company.application.repositories.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    private StudentService studentService;

    @Before
    public void setUp() {
        studentService = new StudentService(studentRepository);
    }

    @Test
    public void testGetStudents() {
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
        when(studentRepository.getStudents()).thenReturn(students);
        final List<Student> result = studentService.getStudents();
        assertEquals(students,result);
    }

    @Test
    public void testGetStudentsNoItems() {
        when(studentRepository.getStudents()).thenReturn(Collections.emptyList());
        final List<Student> result = studentService.getStudents();
        assertEquals(0,result.size());
    }

    @Test
    public void testSaveStudent() {
        final Student student = new Student();
        student.setId(0L);
        student.setFirstName("firstName");
        student.setLastName("lastName");
        student.setEmail("email");

        studentService.saveStudent(student);
        verify(studentRepository).saveStudent(any(Student.class));
    }

    @Test
    public void testGetStudent() {
        final Student student = new Student();
        student.setId(0L);
        student.setFirstName("firstName");
        student.setLastName("lastName");
        student.setEmail("email");
        when(studentRepository.getStudent(0L)).thenReturn(student);

        final Student result = studentService.getStudent(0L);
        assertEquals(student, result);
    }

    @Test
    public void testDeleteStudent() {
        studentService.deleteStudent(0L);
        verify(studentRepository).deleteStudent(0L);
    }
}
