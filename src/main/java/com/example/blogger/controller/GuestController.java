package com.example.blogger.controller;

import com.example.blogger.model.Blog;
import com.example.blogger.model.Post;
import com.example.blogger.repository.BlogRepository;
import com.example.blogger.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class GuestController {

    private BlogRepository blogRepository;
    private PostRepository postRepository;

    @Autowired
    public GuestController(BlogRepository blogRepository, PostRepository postRepository) {

        this.blogRepository = blogRepository;
        this.postRepository = postRepository;
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

    @GetMapping("/blog/{blog_id}")
    public String blogPage(@PathVariable long blog_id, Model model) {
        Optional<Blog> blog = blogRepository.findById(blog_id);
        if(blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            return "blog";
        }
        else {
            return "error404";
        }
    }

    @GetMapping("/blog/post/{post_id}")
    public String postPage(@PathVariable long post_id, Model model) {
        Optional<Post> post = postRepository.findById(post_id);
        if(post.isPresent()) {
            model.addAttribute("post", post.get());
            return "post";
        }
        else {
            return "error404";
        }
    }

}
