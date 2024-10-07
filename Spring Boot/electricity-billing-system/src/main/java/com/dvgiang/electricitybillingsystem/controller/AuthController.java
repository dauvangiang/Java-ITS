package com.dvgiang.electricitybillingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/")
public class AuthController {
    @GetMapping
    public String homePage() {
        return "This is home page for electricity billing system";
    }
}
