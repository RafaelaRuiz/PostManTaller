package com.example.demo.controllers;

import com.example.demo.beans.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
        studentService.saveStudent(new Student("John", "Doe", "userA","pass1"));
        studentService.saveStudent(new Student("Jane", "Doe", "userB","pass2"));
        studentService.saveStudent(new Student("Pedro", "Pascal", "userC","pass3"));
        studentService.saveStudent(new Student("Tom", "Ellis", "userD","pass4"));
        studentService.saveStudent(new Student("Lauren", "German", "userE","pass5"));

    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{firstName}/{lastName}")
    public Student studentPathVariable(@PathVariable("firstName") String firstName,
                                       @PathVariable("lastName") String lastName){
        return studentService.getStudentByName(firstName, lastName);
    }

    @GetMapping("/students/query")
    public Student studentQueryParam(@RequestParam(name="firstname") String firstname, @RequestParam(name="lastname") String lastname){
        return studentService.getStudentByName(firstname, lastname);
    }

    @PostMapping("/student")
    public void addStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
    }

    @PutMapping("/student/{firstName}")
    public void updateStudent(@PathVariable("firstName") String firstName, @RequestBody Student student) {
        studentService.updateStudent(firstName, student);
    }

    @DeleteMapping("/student/{firstName}")
    public void deleteStudent(@PathVariable("firstName") String firstName) {
        studentService.deleteStudent(firstName);
    }
}
