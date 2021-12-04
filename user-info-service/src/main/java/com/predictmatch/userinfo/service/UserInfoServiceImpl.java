package com.predictmatch.userinfo.service;

import com.predictmatch.userinfo.dao.UserInfo;
import com.predictmatch.userinfo.dto.*;
import com.predictmatch.userinfo.exceptions.UserAlreadyExistsException;
import com.predictmatch.userinfo.mapper.Mapper;
import com.predictmatch.userinfo.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    TeamService teamService;

    @Autowired
    ValidationService validationService;

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
    public ResponseEntity<UserInfoResponse> createUser(UserInfoRequest request) {

        if(validationService.checkIfUserAlreadyExists(request.getUsername().trim())) {
            throw new UserAlreadyExistsException( request.getUsername());
        }

       UserInfo user = Mapper.userInfoRequestToEntity( request );

       userInfoRepository.save( user );

       Optional<TeamDto> team = Optional.ofNullable( teamService.findTeam( request.getTeamId() ));
       TeamDto userTeam = null;

       if(team.isPresent())
           userTeam=team.get();

       return ResponseEntity.status( HttpStatus.CREATED ).body( Mapper.userInfoEntityToDto( user,userTeam));

    }
    @Transactional
    @Override
    public ResponseEntity<UserInfoResponse> changeFavouriteTeam(Long id, TeamRequestDto teamRequest) {

        Optional<UserInfo> storedUser = userInfoRepository.findById( id );

        if(storedUser.isEmpty())
            throw new EntityNotFoundException("Not found user with id: "+id);


        UserInfo user = storedUser.get();
        user.setFavoriteTeamId( teamRequest.getId());

        userInfoRepository.save( user );


        TeamDto team = teamService.findTeam(storedUser.get().getFavoriteTeamId());

        return ResponseEntity.ok( Mapper.userInfoEntityToDto(user,team));
    }
    @Transactional
    @Override
    public ResponseEntity<UserInfoResponse> updateUserInfo(Long id, UserInfoRequest request) {

        Optional<UserInfo> storedUser = userInfoRepository.findById( id );

        if(storedUser.isEmpty())
            throw new EntityNotFoundException("Not found user with id: "+id);


        UserInfo user = storedUser.get();

        user.setInfo( request.getInfo());
        user.setCity( request.getCity());
        user.setCountry( request.getCountry() );
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        if(request.getTeamId()!=null && request.getTeamId()!=0)
            user.setFavoriteTeamId( request.getTeamId());

        TeamDto team = teamService.findTeam(user.getFavoriteTeamId());

        userInfoRepository.save( user );

        return ResponseEntity.ok( Mapper.userInfoEntityToDto( user,team ));
    }

    @Transactional
    @Override
    public ResponseEntity<String> removeUser(Long id) {
        Optional<UserInfo> storedUser = userInfoRepository.findById( id );

        if(storedUser.isEmpty())
            return ResponseEntity.status( HttpStatus.NOT_FOUND).body( "User: "+id+" does not exist" );

        userInfoRepository.delete(  storedUser.get());
        return ResponseEntity.status( HttpStatus.ACCEPTED).body( "User "+id+" removed" );
    }
}
