package com.example.blogger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/profile")
    public String profile() {
        return "profile";
    }
}
