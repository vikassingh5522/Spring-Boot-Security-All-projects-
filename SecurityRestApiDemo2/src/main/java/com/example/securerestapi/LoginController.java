package com.example.securerestapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Public Home Page
    @GetMapping("/")
    public String home() {
        return "home"; // maps to home.html
    }

    // Public About Page
    @GetMapping("/about")
    public String about() {
        return "about"; // maps to about.html
    }

    // Custom Login Page
    @GetMapping("/login")
    public String login() {
        return "login"; // maps to login.html
    }

    @GetMapping("/api/greeting")
    public String greeting() {
        return "Hello! You have accessed a secure REST endpoint.";
    }
}
