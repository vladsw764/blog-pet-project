package com.isarev.blog.blogpetproject.controllers;

import com.isarev.blog.blogpetproject.enums.Role;
import com.isarev.blog.blogpetproject.models.User;
import com.isarev.blog.blogpetproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(){
        return "/auth & registr/login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "auth & registr/registration";
    }

    @PostMapping("/registration")
    public String createNewUser(@RequestParam(name = "email") String email,
                                @RequestParam(name = "phoneNumber") String phoneNumber,
                                @RequestParam(name = "username") String username,
                                @RequestParam(name = "password") String password){

        if (!userRepository.existsUserByEmail(email)){
            User user = new User(email, phoneNumber, username, passwordEncoder.encode(password));
            user.getRoles().add(Role.ROLE_USER);
            userRepository.save(user);
        }
        return "redirect:/login";
    }


}
