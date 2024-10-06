package com.example.demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController{
    @GetMapping("/")
    public String message(){
        return "Welcome to Spring Boot Application";
    }
    @GetMapping("/hello-world")
    public String sayHello() {
        return "Hello, World!";
    }

}