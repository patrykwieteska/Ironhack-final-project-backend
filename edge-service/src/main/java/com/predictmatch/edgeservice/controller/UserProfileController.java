package com.predictmatch.edgeservice.controller;

import com.predictmatch.edgeservice.dto.team.TeamRequestDto;
import com.predictmatch.edgeservice.dto.user.UserInfoRequest;
import com.predictmatch.edgeservice.dto.user.UserInfoResponse;
import com.predictmatch.edgeservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserProfileController {

    @Autowired
    UserService userService;

    @GetMapping
    ResponseEntity<UserInfoResponse> findUserByUsername(@RequestParam(name="user") String username) {
        return userService.findProfileByUsername(username);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserInfoResponse> findUserById(@PathVariable(name="id") Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    ResponseEntity<UserInfoResponse> createProfile(@RequestBody @Valid UserInfoRequest request) {
        return userService.createUserProfile(request);
    }

    @PatchMapping("/{userId}")
    ResponseEntity<UserInfoResponse> changeFavoriteTeam(@PathVariable(name="userId") Long id,
                                                        @RequestBody TeamRequestDto team) {
        return userService.changeFavouriteTeam(id, team);
    }
    @PutMapping("/{userId}")
    ResponseEntity<UserInfoResponse> updateUserInfo(@PathVariable(name="userId") Long id,
                                                    @RequestBody UserInfoRequest request) {
       return userService.updateProfile(id,request);
    }

    @DeleteMapping("/{userId}")
    void removeUser(@PathVariable(name="userId") Long id) {
        userService.deleteProfile(id);
    }
}
