package com.company.application.repositories;

import com.company.application.entities.Student;
import com.company.application.repositories.jpa.StudentJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentRepositoryTests {
    @Mock
    private StudentJpaRepository studentJpaRepository;

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new StudentRepository(studentJpaRepository);
    }

    @Test
    void testGetStudents() {
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
        List<Student> students = Arrays.asList(firstStudent,secondStudent);
        when(studentJpaRepository.findAll()).thenReturn(students);
        List<Student> result = studentRepository.getStudents();
        assertEquals(students,result);
    }

    @Test
    void testGetStudentsNoItems() {
        when(studentJpaRepository.findAll()).thenReturn(Collections.emptyList());
        final List<Student> result = studentRepository.getStudents();
        assertEquals(0,result.size());
    }

    @Test
    void testSaveStudent() {
        final Student student = new Student();
        student.setId(0L);
        student.setFirstName("firstName");
        student.setLastName("lastName");
        student.setEmail("email");
        final Student student1 = new Student();
        student1.setId(0L);
        student1.setFirstName("firstName");
        student1.setLastName("lastName");
        student1.setEmail("email");
        when(studentJpaRepository.save(any(Student.class))).thenReturn(student1);
        studentRepository.saveStudent(student);
        verify(studentJpaRepository).save(student);
    }

    @Test
    void testGetStudent() {
        final Student student1 = new Student();
        student1.setId(0L);
        student1.setFirstName("firstName");
        student1.setLastName("lastName");
        student1.setEmail("email");
        final Optional<Student> student = Optional.of(student1);
        when(studentJpaRepository.findById(0L)).thenReturn(student);
        final Student result = studentRepository.getStudent(0L);
        assertEquals(student,Optional.of(result));
    }

    @Test
    void testGetStudentNoItem() {
        when(studentJpaRepository.findById(0L)).thenReturn(Optional.empty());
        final Student result = studentRepository.getStudent(0L);
        assertEquals(null,result);
    }

    @Test
    void testDeleteStudent() {
        studentRepository.deleteStudent(0L);
        verify(studentJpaRepository).deleteById(0L);
    }
}
