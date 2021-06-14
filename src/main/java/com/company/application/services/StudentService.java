package com.company.application.services;

import com.company.application.entities.Student;
import com.company.application.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }

    @Transactional
    public void saveStudent(Student student) {
        studentRepository.saveStudent(student);
    }

    @Transactional
    public Student getStudent(Long id) {
        return studentRepository.getStudent(id);
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteStudent(id);
    }
}
