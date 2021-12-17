package com.predictmatch.edgeservice.controller;

import com.predictmatch.edgeservice.dto.team.TeamRequestDto;
import com.predictmatch.edgeservice.dto.user.UserInfoRequest;
import com.predictmatch.edgeservice.dto.user.UserInfoResponse;
import com.predictmatch.edgeservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserProfileController {

    @Autowired
    UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserInfoResponse> findUserByUsername(@RequestParam(name="user") String username) {
        return userService.findProfileByUsername(username);
    }

//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.CREATED)
//    ResponseEntity<UserInfoResponse> findUserById(@PathVariable(name="id") Long id) {
//        return userService.findUserById(id);
//    }

    @PatchMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserInfoResponse> changeFavoriteTeam(@PathVariable(name="username") String username,
                                                        @RequestBody TeamRequestDto team,@RequestHeader("Authorization") String token) {
        return userService.changeFavouriteTeam(username, team,token);
    }
    @PutMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserInfoResponse> updateUserInfo(@PathVariable(name="username") String username,
                                                    @RequestBody UserInfoRequest request,@RequestHeader("Authorization") String token) {
       return userService.updateProfile(username,request,token);
    }

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ResponseEntity<String> removeUser(@PathVariable(name="username") String username,
                                      @RequestHeader("Authorization") String token) {
        return userService.deleteProfile(username,token);
    }
}
