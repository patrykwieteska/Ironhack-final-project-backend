package com.predictmatch.userinfo.controller;

import com.predictmatch.userinfo.dto.TeamRequestDto;
import com.predictmatch.userinfo.dto.UserInfoRequest;
import com.predictmatch.userinfo.dto.UserInfoResponse;
import com.predictmatch.userinfo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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


    @PatchMapping("/{userId}/team")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<?> changeFavoriteTeam(@PathVariable(name="userId") String username,
                                                        @RequestBody TeamRequestDto team,@RequestHeader("Authorization") String token) {
        return userInfoService.changeFavouriteTeam(username, team,token);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<?> updateUserInfo(@PathVariable(name="userId") String username,
                                    @RequestBody UserInfoRequest request,@RequestHeader("Authorization") String token) {
        return userInfoService.updateUserInfo(username,request,token);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<String> removeUser(@PathVariable(name="userId") String username,@RequestHeader("Authorization") String token) {

        return userInfoService.removeUser(username,token);
    }
}
