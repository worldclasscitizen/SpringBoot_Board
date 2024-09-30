package com.example.individual.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String welcomeP() {
        return "main/welcome";
    }

    @GetMapping("/login")
    public String loginP() {
        return "/accounts/login";
    }

    @GetMapping("/signup")
    public String signupP() {
        return "/accounts/signup";
    }
}
