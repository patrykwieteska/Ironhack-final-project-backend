package com.predictmatch.edgeservice.proxy;

import com.predictmatch.edgeservice.dto.team.TeamRequestDto;
import com.predictmatch.edgeservice.dto.user.UserInfoRequest;
import com.predictmatch.edgeservice.dto.user.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient("user-info-service")
public interface UserProxy {


    @GetMapping("/userinfo/api/v1/users")
    ResponseEntity<UserInfoResponse> findUserByUsername(@RequestParam(name="user") String username);

    @GetMapping("/userinfo/api/v1/users/{id}")
    ResponseEntity<UserInfoResponse> findUserById(@PathVariable(name="id") Long id);

    @PostMapping("/userinfo/api/v1/users")
    ResponseEntity<UserInfoResponse> createUser(@RequestBody @Valid UserInfoRequest request);

    @PatchMapping("/userinfo/api/v1/users/{userId}/team")
    ResponseEntity<UserInfoResponse> changeFavoriteTeam(@PathVariable(name="userId") Long id,
                                                        @RequestBody TeamRequestDto team);
    @PutMapping("/userinfo/api/v1/users/{userId}")
    ResponseEntity<UserInfoResponse> updateUserInfo(@PathVariable(name="userId") Long id,
                                                    @RequestBody UserInfoRequest request);
    @DeleteMapping("/userinfo/api/v1/users/{userId}")
    ResponseEntity<String> removeUser(@PathVariable(name="userId") Long id); //wireframes, FIGMA
}
