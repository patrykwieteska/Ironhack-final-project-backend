package com.predictmatch.userinfo.controller;

import com.predictmatch.userinfo.dto.auth.RegisterRequest;
import com.predictmatch.userinfo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/userinfo/api/v1/")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody @Valid RegisterRequest registerRequest) {

        return authService.registerUser(registerRequest);
    }

}
