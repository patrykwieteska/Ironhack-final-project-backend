package com.predictmatch.edgeservice.services;

import com.predictmatch.edgeservice.dto.team.TeamRequestDto;
import com.predictmatch.edgeservice.dto.user.UserInfoRequest;
import com.predictmatch.edgeservice.dto.user.UserInfoResponse;
import com.predictmatch.edgeservice.proxy.UserProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public static final Logger logger = LoggerFactory.getLogger( UserService.class );

    @Autowired
    UserProxy userProxy;

    public ResponseEntity<UserInfoResponse> findProfileByUsername(String username) {
        return userProxy.findUserByUsername( username );
    }

    public ResponseEntity<UserInfoResponse> createUserProfile(UserInfoRequest request) {
       return userProxy.createUser( request );
    }

    public ResponseEntity<UserInfoResponse> changeFavouriteTeam(Long id, TeamRequestDto team) {
        return userProxy.changeFavoriteTeam( id,team );
    }

    public ResponseEntity<UserInfoResponse> updateProfile(Long id, UserInfoRequest request) {
        return userProxy.updateUserInfo( id,request );
    }

    public ResponseEntity<String> deleteProfile(Long id) {
        return userProxy.removeUser( id );
    }


    public ResponseEntity<UserInfoResponse> findUserById(Long id) {
        return userProxy.findUserById( id );
    }
}
