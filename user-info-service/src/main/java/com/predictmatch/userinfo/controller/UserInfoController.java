package com.predictmatch.userinfo.controller;

import com.predictmatch.userinfo.dto.UserInfoRequest;
import com.predictmatch.userinfo.dto.UserInfoResponse;
import com.predictmatch.userinfo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/userinfo/api/v1/users")
public class UserInfoController {


    @Autowired
    UserInfoService userInfoService;


    @GetMapping
    UserInfoResponse findUserByName() {
        return null;
    }

    @PostMapping
    UserInfoResponse createUser(@RequestBody @Valid UserInfoRequest request) {
        return null;
    }

    @PatchMapping("/{userId}")
    UserInfoResponse changeFavoriteTeam(@PathVariable(name="userId") Long id) {
        return null;
    }

    @PostMapping
    UserInfoResponse updateUserInfo() {
        return null;
    }

    @DeleteMapping("/{userId}")
    void removeUser() {

    }



}
