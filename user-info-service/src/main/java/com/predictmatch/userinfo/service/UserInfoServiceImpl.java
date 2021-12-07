package com.predictmatch.userinfo.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.predictmatch.userinfo.dao.UserInfo;
import com.predictmatch.userinfo.dao.auth.User;
import com.predictmatch.userinfo.dto.TeamDto;
import com.predictmatch.userinfo.dto.TeamRequestDto;
import com.predictmatch.userinfo.dto.UserInfoRequest;
import com.predictmatch.userinfo.dto.UserInfoResponse;
import com.predictmatch.userinfo.dto.auth.RegisterRequest;
import com.predictmatch.userinfo.exceptions.UserAlreadyExistsException;
import com.predictmatch.userinfo.mapper.Mapper;
import com.predictmatch.userinfo.repository.UserInfoRepository;
import com.predictmatch.userinfo.security.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    TeamService teamService;

    @Autowired
    ValidationService validationService;

    @Autowired
    JwtUtils jwtUtils;

    @Transactional
    @Override
    public ResponseEntity<UserInfoResponse> findUserByUsername(String username) {
        Optional<UserInfo> storedUser = Optional.ofNullable(userInfoRepository.findByUsername( username ));

        if(storedUser.isEmpty())
            throw new EntityNotFoundException("Not found username: "+username);

        TeamDto team = teamService.findTeam(storedUser.get().getFavoriteTeamId());

        return ResponseEntity.ok( Mapper.userInfoEntityToDto( storedUser.get(),team));


    }
    @Transactional
    @Override
    public ResponseEntity<UserInfoResponse> findUserById(Long id) {
        Optional<UserInfo> storedUser = userInfoRepository.findById( id );

        if(storedUser.isEmpty())
            throw new EntityNotFoundException("Not found user with id: "+id);


        TeamDto team = teamService.findTeam(storedUser.get().getFavoriteTeamId());


        return ResponseEntity.ok( Mapper.userInfoEntityToDto( storedUser.get(),team ));

    }

    @Transactional
    @Override
    public UserInfoResponse createUserProfile(RegisterRequest request, User user) {

        if(validationService.checkIfUserAlreadyExists(request.getUsername().trim())) {
            throw new UserAlreadyExistsException( request.getUsername());
        }

       UserInfo userInfo = Mapper.registerRequestToUserInfoEntity( request,user );
        log.info("User profile is creating...");
       userInfoRepository.save( userInfo );

       Optional<TeamDto> team = Optional.ofNullable( teamService.findTeam( request.getTeamId() ));
       TeamDto userTeam = null;

       if(team.isPresent()) {
           userTeam=team.get();
           log.info( "Team {} added to user profile",team.get().getName() );
       } else {
           log.error( "Currently cannot find team with id: "+request.getTeamId());
       }


       return Mapper.userInfoEntityToDto( userInfo,userTeam);

    }

    @Transactional
    @Override
    public ResponseEntity<?> changeFavouriteTeam(String username, TeamRequestDto teamRequest, String token) {

        DecodedJWT decodedToken = jwtUtils.decodeJwtToken( token );

        long tokenUserId = Long.parseLong(decodedToken.getClaim( "user_id").toString());

        String tokenUsername = decodedToken.getSubject();



        if(!tokenUsername.equals( username ))
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED.value()).body( "Error: Unauthorized operation!");

        Optional<UserInfo> storedUser = userInfoRepository.findById( tokenUserId );

        if(storedUser.isEmpty())
            throw new EntityNotFoundException("Not found user with id: "+tokenUserId);

        UserInfo user = storedUser.get();

        user.setFavoriteTeamId( teamRequest.getId());
        userInfoRepository.saveAndFlush( user );


        TeamDto team = teamService.findTeam(user.getFavoriteTeamId());

        return ResponseEntity.ok( Mapper.userInfoEntityToDto(user,team));
    }
    @Transactional
    @Override
    public ResponseEntity<?> updateUserInfo(String username, UserInfoRequest request, String token) {

        DecodedJWT decodedToken = jwtUtils.decodeJwtToken( token );

        long tokenUserId = Long.parseLong(decodedToken.getClaim( "user_id").toString());

        String tokenUsername = decodedToken.getSubject();

        if(!tokenUsername.equals(username  ))
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED.value()).body( "Error: Unauthorized operation!");


        Optional<UserInfo> storedUser = userInfoRepository.findById( tokenUserId );

        if(storedUser.isEmpty())
            throw new EntityNotFoundException("Not found user with id: "+tokenUserId);

        UserInfo user = storedUser.get();

        if(request.getUsername() !=null) {
            if(validationService.checkIfUserAlreadyExists(request.getUsername().trim()) && !request.getUsername().equals( user.getUsername())) {
                throw new UserAlreadyExistsException( request.getUsername());
            }
            user.setUsername(request.getUsername());
        }

        if(request.getInfo()!=null) {
            user.setInfo( request.getInfo());
        }

        if(request.getCity() !=null) {
            user.setCity( request.getCity());
        }

        if(request.getCountry() !=null) {
            user.setCountry( request.getCountry() );
        }

        if(request.getEmail() !=null) {
            user.setEmail(request.getEmail());
        }

        if(request.getTeamId()!=null && request.getTeamId()!=0) {
            user.setFavoriteTeamId( request.getTeamId());
        }

        TeamDto team = teamService.findTeam(user.getFavoriteTeamId());
        userInfoRepository.save( user );
        return ResponseEntity.ok( Mapper.userInfoEntityToDto( user,team ));
    }

    @Transactional
    @Override
    public ResponseEntity<String> removeUser(String username, String token) {

        DecodedJWT decodedToken = jwtUtils.decodeJwtToken( token );

        long tokenUserId = Long.parseLong(decodedToken.getClaim( "user_id").toString());

        String tokenUsername = decodedToken.getSubject();

        if(!tokenUsername.equals(username  ))
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED.value()).body( "Error: Unauthorized operation!");


        Optional<UserInfo> storedUser = userInfoRepository.findById( tokenUserId );

        if(storedUser.isEmpty())
            return ResponseEntity.status( HttpStatus.NOT_FOUND).body( "User: "+username+" does not exist" );

        userInfoRepository.delete(  storedUser.get());
        return ResponseEntity.status( HttpStatus.ACCEPTED).body( "User with id: "+username+" was removed" );
    }
}
