package com.predictmatch.edgeservice.services;

import com.predictmatch.edgeservice.dto.team.TeamRequestDto;
import com.predictmatch.edgeservice.dto.user.UserInfoRequest;
import com.predictmatch.edgeservice.dto.user.UserInfoResponse;
import com.predictmatch.edgeservice.proxy.PredictionProxy;
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

    @Autowired
    PredictionProxy predictionProxy;

    public ResponseEntity<UserInfoResponse> findProfileByUsername(String username) {

        UserInfoResponse userInfoResponse= userProxy.findUserByUsername( username ).getBody();


        if(userInfoResponse!=null)
        userInfoResponse
                .setPredictionHistory(
                        predictionProxy.getUserPredictionsHistory( userInfoResponse.getUserId())
                                .getBody());

        return ResponseEntity.ok(userInfoResponse);
    }

    public ResponseEntity<UserInfoResponse> createUserProfile(UserInfoRequest request) {

        UserInfoResponse userInfoResponse=userProxy.createUser( request ).getBody();

        if(userInfoResponse!=null)
            userInfoResponse
                    .setPredictionHistory(
                            predictionProxy.getUserPredictionsHistory( userInfoResponse.getUserId())
                                    .getBody());

        return ResponseEntity.ok(userInfoResponse);
    }

    public ResponseEntity<UserInfoResponse> changeFavouriteTeam(Long id, TeamRequestDto team) {

        UserInfoResponse userInfoResponse=userProxy.changeFavoriteTeam( id,team ).getBody();
        if(userInfoResponse!=null)
            userInfoResponse
                    .setPredictionHistory(
                            predictionProxy.getUserPredictionsHistory( userInfoResponse.getUserId())
                                    .getBody());

        return ResponseEntity.ok(userInfoResponse);
    }

    public ResponseEntity<UserInfoResponse> updateProfile(Long id, UserInfoRequest request) {

        UserInfoResponse userInfoResponse=userProxy.updateUserInfo( id,request ).getBody();
        if(userInfoResponse!=null)
            userInfoResponse
                    .setPredictionHistory(
                            predictionProxy.getUserPredictionsHistory( userInfoResponse.getUserId())
                                    .getBody());

        return ResponseEntity.ok(userInfoResponse);


    }

    public ResponseEntity<String> deleteProfile(Long id) {
        return userProxy.removeUser( id );
    }


    public ResponseEntity<UserInfoResponse> findUserById(Long id) {

        UserInfoResponse userInfoResponse= userProxy.findUserById( id ).getBody();
        if(userInfoResponse!=null)
            userInfoResponse
                    .setPredictionHistory(
                            predictionProxy.getUserPredictionsHistory( userInfoResponse.getUserId())
                                    .getBody());

        return ResponseEntity.ok(userInfoResponse);

    }
}
