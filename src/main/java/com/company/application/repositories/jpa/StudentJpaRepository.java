package com.company.application.repositories.jpa;

import com.company.application.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {
}
