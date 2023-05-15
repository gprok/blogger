package com.example.blogger.controller;

import com.example.blogger.model.Blog;
import com.example.blogger.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Controller
public class GuestController {

    private BlogRepository blogRepository;

    @Autowired
    public GuestController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/blogs")
    public String blogs(Model model) {
        List<Blog> blogs = blogRepository.findAll();
        model.addAttribute("blogs", blogs);
        return "blogs";
    }
}
