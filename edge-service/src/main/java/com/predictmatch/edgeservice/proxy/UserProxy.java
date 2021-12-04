package com.predictmatch.edgeservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("user-info-service")
public interface UserProxy {


    @GetMapping
    ResponseEntity<UserInfoResponse> findUserByUsername(@RequestParam(name="user") String username);

    @GetMapping("/{id}")
    ResponseEntity<UserInfoResponse> findUserById(@PathVariable(name="id") Long id);

    @PostMapping
    ResponseEntity<CreatedUserInfo> createUser(@RequestBody @Valid UserInfoRequest request);

    @PatchMapping("/{userId}")
    ResponseEntity<UserInfoResponse> changeFavoriteTeam(@PathVariable(name="userId") Long id,
                                                        @RequestBody TeamRequestDto team);
    @PutMapping("/{userId}")
    ResponseEntity<UserInfoResponse> updateUserInfo(@PathVariable(name="userId") Long id,
                                                    @RequestBody UserInfoRequest request);

    @DeleteMapping("/{userId}")
    void removeUser(@PathVariable(name="userId") Long id);
}
