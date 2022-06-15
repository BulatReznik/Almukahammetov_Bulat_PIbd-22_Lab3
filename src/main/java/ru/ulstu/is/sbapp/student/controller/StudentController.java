package ru.ulstu.is.sbapp.student.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.student.model.Student;
import ru.ulstu.is.sbapp.student.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController { ////////////OLD
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @GetMapping("/")
    public List<Student> getStudents() {
        return studentService.findAllStudents();
    }

    @PostMapping("/")
    public Student createStudent(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        return studentService.addStudent(firstName, lastName);
    }

    @PatchMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,
                                 @RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        return studentService.updateStudent(id, firstName, lastName);
    }

    @DeleteMapping("/{id}")
    public Student deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }
}