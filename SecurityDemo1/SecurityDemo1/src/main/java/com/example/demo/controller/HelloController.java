package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Spring Boot with Default Security!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "This is an Admin page!";
    }

    @GetMapping("/about")
    public String about() {
        return "This is the about page!";
    }

    @GetMapping("/contact")
    public String contact() {
        return "This is the contact page!";
    }

    @GetMapping("/mean")
    public String mean() {
        return "This is the mean page!";
    }

    @GetMapping("/xyz")
    public String xyz() {
        return "This is the xyz page!";
    }
}
