package com.predictmatch.edgeservice.controller;

import com.predictmatch.edgeservice.dto.TokenResponse;
import com.predictmatch.edgeservice.dto.user.LoginRequest;
import com.predictmatch.edgeservice.dto.user.RegisterRequest;
import com.predictmatch.edgeservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    ResponseEntity<TokenResponse> signIn(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.signIn(loginRequest);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> registerUser(@RequestBody @Valid RegisterRequest request) {
        return userService.registerNewUser(request);
    }
}
