package com.example.task_time_tracker.controller;

import com.example.task_time_tracker.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @GetMapping("/api/auth/token")
    public String getToken(@RequestParam String username) {
        return jwtUtil.generateToken(username);
    }
}