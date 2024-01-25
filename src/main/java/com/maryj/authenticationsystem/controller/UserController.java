package com.maryj.authenticationsystem.controller;

import com.maryj.authenticationsystem.dto.Request;
import com.maryj.authenticationsystem.dto.Response;
import com.maryj.authenticationsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Response> signUp(@RequestBody Request request) {
       return userService.signUp(request);
    }
}
