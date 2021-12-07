package com.predictmatch.userinfo.service;

import com.predictmatch.userinfo.dao.auth.User;
import com.predictmatch.userinfo.dto.*;
import com.predictmatch.userinfo.dto.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface UserInfoService {
    ResponseEntity<UserInfoResponse> findUserByUsername(String username);

    ResponseEntity<UserInfoResponse> findUserById(Long id);

    UserInfoResponse createUserProfile(RegisterRequest request, User user);

    ResponseEntity<?> changeFavouriteTeam(String username, TeamRequestDto team, String token);

    ResponseEntity<?> updateUserInfo(String username, UserInfoRequest request, String token);

    ResponseEntity<String> removeUser(String username, String token);
}
