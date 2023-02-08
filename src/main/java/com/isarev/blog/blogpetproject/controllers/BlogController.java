package com.isarev.blog.blogpetproject.controllers;

import com.isarev.blog.blogpetproject.models.Post;
import com.isarev.blog.blogpetproject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }
    @GetMapping("/blog/add")
    public String addBlog(Model model){
        return "blog-add";
    }
    @PostMapping("/blog/add")
    public String addBlogPost(@RequestParam String title, @RequestParam String anons,
                              @RequestParam String full_text, Model model){
        Post post = new Post(title, anons, full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }
    @GetMapping("/blog/{id}")
    public String moreInfo(@PathVariable(value = "id") long id, Model model){

        if (!postRepository.existsById(id)) return "redirect:/blog";

        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-details";
    }
    @GetMapping("/blog/{id}/edit")
    public String editPost(@PathVariable(value = "id") long id, Model model){
        if (!postRepository.existsById(id)) return "redirect:/blog";

        Optional<Post> post = postRepository.findById(id);
        List<Post> posts = new ArrayList<>();
        post.ifPresent(posts::add);
        model.addAttribute("post", posts);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String editBlogPost(@PathVariable(value = "id") long id, Model model,
                               @RequestParam String title, @RequestParam String anons, @RequestParam String full_text){
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/delete")
    public String deletePost(@PathVariable(value = "id") long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }
}
