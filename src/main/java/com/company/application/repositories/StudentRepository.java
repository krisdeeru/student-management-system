package com.company.application.repositories;

import com.company.application.entities.Student;
import com.company.application.repositories.jpa.StudentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    private final StudentJpaRepository repository;

    @Autowired
    public StudentRepository(StudentJpaRepository repository) {
        this.repository=repository;
    }

    public List<Student> getStudents() {
        return repository.findAll();
    }

    public void saveStudent(Student student) {
        repository.save(student);
    }

    public Student getStudent(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }
}
