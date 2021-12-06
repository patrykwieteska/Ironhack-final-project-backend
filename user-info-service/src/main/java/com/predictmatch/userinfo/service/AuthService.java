package com.predictmatch.userinfo.service;

import com.predictmatch.userinfo.dao.auth.Role;
import com.predictmatch.userinfo.dao.auth.User;
import com.predictmatch.userinfo.dto.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthService {

    ResponseEntity<?> registerUser(RegisterRequest registerRequest);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User> getUsers();
}
