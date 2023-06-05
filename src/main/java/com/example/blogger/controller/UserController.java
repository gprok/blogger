package com.example.blogger.controller;

import com.example.blogger.dto.PostDto;
import com.example.blogger.model.Blog;
import com.example.blogger.model.Post;
import com.example.blogger.model.User;
import com.example.blogger.repository.BlogRepository;
import com.example.blogger.repository.PostRepository;
import com.example.blogger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;
import java.util.Set;

@Controller
public class UserController {

    private UserRepository userRepository;
    private BlogRepository blogRepository;
    private PostRepository postRepository;

    @Autowired
    public UserController(UserRepository userRepository, BlogRepository blogRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/user/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/user/add/post")
    public String addPost(Model model, Authentication authentication) {
        PostDto postDto = new PostDto();
        User user = userRepository.findByEmail(authentication.getName());
        Set<Blog> blogs = user.getBlogs();

        model.addAttribute("postDto", postDto);
        model.addAttribute("blogs", blogs);

        return "add_post";
    }

    @PostMapping("/user/post/save")
    public String savePost(@ModelAttribute PostDto postDto, Model model, Authentication authentication) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        Optional<Blog> blog = blogRepository.findById(postDto.getBlog_id());

        if(blog.isPresent()) {
            post.setBlog(blog.get());
            postRepository.save(post);
            return "redirect:/user/add/post?success";
        }
        else {
            return "redirect:/user/add/post?error";
        }
    }
}
