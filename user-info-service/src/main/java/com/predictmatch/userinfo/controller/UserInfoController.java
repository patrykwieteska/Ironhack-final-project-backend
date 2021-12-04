package com.predictmatch.userinfo.controller;

import com.predictmatch.userinfo.dto.*;
import com.predictmatch.userinfo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/userinfo/api/v1/users")
public class UserInfoController {


    @Autowired
    UserInfoService userInfoService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserInfoResponse> findUserByUsername(@RequestParam(name="user") String username) {
        return userInfoService.findUserByUsername(username);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserInfoResponse> findUserById(@PathVariable(name="id") Long id) {
        return userInfoService.findUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<UserInfoResponse> createUser(@RequestBody @Valid UserInfoRequest request) {
        return userInfoService.createUser(request);
    }

    @PatchMapping("/{userId}/team")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserInfoResponse> changeFavoriteTeam(@PathVariable(name="userId") Long id,
                                                        @RequestBody TeamRequestDto team) {
        return userInfoService.changeFavouriteTeam(id, team);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserInfoResponse> updateUserInfo(@PathVariable(name="userId") Long id,
                                    @RequestBody UserInfoRequest request) {
        return userInfoService.updateUserInfo(id,request);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ResponseEntity<String> removeUser(@PathVariable(name="userId") Long id) {
        return userInfoService.removeUser(id);
    }
}
