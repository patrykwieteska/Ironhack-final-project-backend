package com.predictmatch.edgeservice.controller;

import com.predictmatch.edgeservice.dto.team.TeamRequestDto;
import com.predictmatch.edgeservice.dto.user.UserInfoRequest;
import com.predictmatch.edgeservice.dto.user.UserInfoResponse;
import com.predictmatch.edgeservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserProfileController {

    @Autowired
    UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserInfoResponse> findUserByUsername(@RequestParam(name="user") String username) {
        return userService.findProfileByUsername(username);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<UserInfoResponse> findUserById(@PathVariable(name="id") Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserInfoResponse> createProfile(@RequestBody @Valid UserInfoRequest request) {
        return userService.createUserProfile(request);
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserInfoResponse> changeFavoriteTeam(@PathVariable(name="userId") Long id,
                                                        @RequestBody TeamRequestDto team) {
        return userService.changeFavouriteTeam(id, team);
    }
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserInfoResponse> updateUserInfo(@PathVariable(name="userId") Long id,
                                                    @RequestBody UserInfoRequest request) {
       return userService.updateProfile(id,request);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ResponseEntity<String> removeUser(@PathVariable(name="userId") Long id) {
        return userService.deleteProfile(id);
    }
}
