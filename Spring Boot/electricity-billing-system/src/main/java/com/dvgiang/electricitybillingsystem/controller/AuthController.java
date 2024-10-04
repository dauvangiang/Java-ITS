package com.dvgiang.electricitybillingsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/home")
    public String home() {
        return "Hello, HOME PAGE";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "Hello, LOGIN PAGE";
    }
}
