package com.example.demo.services;

import com.example.demo.beans.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class StudentService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUsername(username);
        if (student == null) {
            throw new UsernameNotFoundException("Student not found");
        }
        UserBuilder builder = withUsername(username);
        builder.password(student.getPassword());
        builder.roles("USER");
        return builder.build();
    }


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentByName(String firstName, String lastName) {
        return studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public void saveStudent(Student student) {
        saveMethod(student, passwordEncoder);
    }
    public void saveMethod(Student student, PasswordEncoder passwordEncoder) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentRepository.save(student);
    }

    public void updateStudent(String firstName, Student updatedStudent) {
        Student student = studentRepository.findByFirstName(firstName);
        if (student != null) {
            student.setFirstName(updatedStudent.getFirstName());
            student.setLastName(updatedStudent.getLastName());
            studentRepository.save(student);
        }
    }

    public void deleteStudent(String firstName) {
        studentRepository.deleteByFirstName(firstName);
    }


}