package com.example.banking.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Secured("ROLE_ADMIN")
    @GetMapping("/accounts")
    public String listAccounts() {
        return "List of all accounts (Admin only)";
    }
}
