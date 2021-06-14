package com.company.application.controllers;

import com.company.application.entities.Student;
import com.company.application.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public String listStudents(Model model) {
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        return "list-students";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student-form";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/student/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") Long id, Model model) {
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        return "student-form";
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("studentId") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/student/list";
    }
}
