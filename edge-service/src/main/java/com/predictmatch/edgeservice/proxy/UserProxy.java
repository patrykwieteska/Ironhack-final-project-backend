package com.predictmatch.edgeservice.proxy;

import com.predictmatch.edgeservice.dto.TokenResponse;
import com.predictmatch.edgeservice.dto.team.TeamRequestDto;
import com.predictmatch.edgeservice.dto.user.*;
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

    @PostMapping("/userinfo/api/v1/register")
    ResponseEntity<UserInfoResponse> registerUser(@RequestBody @Valid RegisterRequest request);

    @PatchMapping("/userinfo/api/v1/users/{userId}/team")
    ResponseEntity<UserInfoResponse> changeFavoriteTeam(@PathVariable(name="userId") String username,
                                                        @RequestBody TeamRequestDto team, @RequestHeader("Authorization") String bearerToken);
    @PutMapping("/userinfo/api/v1/users/{userId}")
    ResponseEntity<UserInfoResponse> updateUserInfo(@PathVariable(name="userId") String username,
                                                    @RequestBody UserInfoRequest request, @RequestHeader("Authorization") String bearerToken);
    @DeleteMapping("/userinfo/api/v1/users/{userId}")
    ResponseEntity<String> removeUser(@PathVariable(name="userId") String username, @RequestHeader("Authorization") String bearerToken);

    @PostMapping("/userinfo/api/v1/login")
    TokenResponse login(@RequestBody @Valid LoginRequest loginRequest);

    @PostMapping("/userinfo/api/v1/verify")
    public ResponseEntity<Boolean> verifyUsername(@RequestBody @Valid UserVerificationRequest verifyUser,
                                            @RequestHeader("Authorization") String bearerToken);
}
