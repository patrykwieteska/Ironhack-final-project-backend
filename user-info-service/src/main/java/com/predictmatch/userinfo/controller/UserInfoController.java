package com.predictmatch.userinfo.controller;

import com.predictmatch.userinfo.dto.*;
import com.predictmatch.userinfo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/userinfo/api/v1/users")
public class UserInfoController {


    @Autowired
    UserInfoService userInfoService;


    @GetMapping
    ResponseEntity<UserInfoResponse> findUserByUsername(@RequestParam(name="user") String username) {
        return userInfoService.findUserByUsername(username);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserInfoResponse> findUserById(@PathVariable(name="id") Long id) {
        return userInfoService.findUserById(id);
    }

    @PostMapping
    ResponseEntity<UserInfoResponse> createUser(@RequestBody @Valid UserInfoRequest request) {
        return userInfoService.createUser(request);
    }

    @PatchMapping("/{userId}")
    ResponseEntity<UserInfoResponse> changeFavoriteTeam(@PathVariable(name="userId") Long id,
                                                        @RequestBody TeamRequestDto team) {
        return userInfoService.changeFavouriteTeam(id, team);
    }

    @PutMapping("/{userId}")
    ResponseEntity<UserInfoResponse> updateUserInfo(@PathVariable(name="userId") Long id,
                                    @RequestBody UserInfoRequest request) {
        return userInfoService.updateUserInfo(id,request);
    }

    @DeleteMapping("/{userId}")
    void removeUser(@PathVariable(name="userId") Long id) {
        userInfoService.removeUser(id);
    }
}
