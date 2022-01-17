package com.example.kubeioauth.controller;

import com.example.kubeioauth.models.User;
import com.example.kubeioauth.services.UserService;
import com.example.kubeioauth.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class RestAuthController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    public RestAuthController(final JwtUtil jwtUtil, final UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        if(userService.isRegisteredUser(user)) {
            final String token = jwtUtil.generateToken(user);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("User not registered", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        User registeredUser = userService.createUser(user);
        return new ResponseEntity<>("Registered user " + registeredUser, HttpStatus.OK);
    }
}