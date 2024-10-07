package com.example.demo.repositories;

import com.example.demo.beans.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByFirstName(String firstName);
    Student findByLastName(String lastName);
    Student findByUsername(String username);
    Student findByFirstNameAndLastName(String firstName, String lastName);

    void deleteByFirstName(String firstName);
}
