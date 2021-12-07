package com.predictmatch.edgeservice.services;

import com.predictmatch.edgeservice.dto.TokenResponse;
import com.predictmatch.edgeservice.dto.prediction.UserPredictionHistoryDto;
import com.predictmatch.edgeservice.dto.team.TeamRequestDto;
import com.predictmatch.edgeservice.dto.user.LoginRequest;
import com.predictmatch.edgeservice.dto.user.RegisterRequest;
import com.predictmatch.edgeservice.dto.user.UserInfoRequest;
import com.predictmatch.edgeservice.dto.user.UserInfoResponse;
import com.predictmatch.edgeservice.proxy.PredictionProxy;
import com.predictmatch.edgeservice.proxy.UserProxy;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service @Slf4j
public class UserService {

    public static final Logger logger = LoggerFactory.getLogger( UserService.class );

    @Autowired
    UserProxy userProxy;

    @Autowired
    PredictionProxy predictionProxy;

    public ResponseEntity<UserInfoResponse> findProfileByUsername(String username) {
        log.info( "Searching for profile by username" );
        UserInfoResponse userInfoResponse= userProxy.findUserByUsername( username).getBody();


        if(userInfoResponse!=null)
        userInfoResponse
                .setPredictionHistory(
                        predictionProxy.getUserPredictionsHistory( userInfoResponse.getUsername()));

        return ResponseEntity.ok(userInfoResponse);
    }

    public ResponseEntity<UserInfoResponse> registerNewUser(RegisterRequest request) {
        log.info( "Registering new user" );
        UserInfoResponse userInfoResponse=userProxy.registerUser( request ).getBody();

        if(userInfoResponse!=null) {
            UserPredictionHistoryDto userPredictionHistory = predictionProxy.getUserPredictionsHistory( userInfoResponse.getUsername());
            userInfoResponse
                    .setPredictionHistory(userPredictionHistory);
        }

        return ResponseEntity.ok(userInfoResponse);
    }

    public ResponseEntity<UserInfoResponse> changeFavouriteTeam(String username, TeamRequestDto team, String token) {

        UserInfoResponse userInfoResponse=userProxy.changeFavoriteTeam( username,team ,token).getBody();
        if(userInfoResponse!=null)
            userInfoResponse
                    .setPredictionHistory(
                            predictionProxy.getUserPredictionsHistory( userInfoResponse.getUsername()));

        return ResponseEntity.ok(userInfoResponse);
    }

    public ResponseEntity<UserInfoResponse> updateProfile(String username, UserInfoRequest request, String token) {

        UserInfoResponse userInfoResponse=userProxy.updateUserInfo( username,request,token ).getBody();
        if(userInfoResponse!=null)
            userInfoResponse
                    .setPredictionHistory(
                            predictionProxy.getUserPredictionsHistory( userInfoResponse.getUsername()));

        return ResponseEntity.ok(userInfoResponse);


    }

    public ResponseEntity<String> deleteProfile(String username, String token) {
        return userProxy.removeUser( username ,token);
    }


    public ResponseEntity<UserInfoResponse> findUserById(Long id) {

        UserInfoResponse userInfoResponse= userProxy.findUserById( id ).getBody();
        if(userInfoResponse!=null)
            userInfoResponse
                    .setPredictionHistory(
                            predictionProxy.getUserPredictionsHistory( userInfoResponse.getUsername()));

        return ResponseEntity.ok(userInfoResponse);

    }

    public ResponseEntity<TokenResponse> signIn(LoginRequest loginRequest) {

        return ResponseEntity.ok(userProxy.login(loginRequest));

    }
}
