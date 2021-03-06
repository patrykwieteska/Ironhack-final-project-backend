package com.predictmatch.userinfo.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.predictmatch.userinfo.dao.auth.Role;
import com.predictmatch.userinfo.dao.auth.User;
import com.predictmatch.userinfo.dto.MessageResponse;
import com.predictmatch.userinfo.dto.UserInfoResponse;
import com.predictmatch.userinfo.dto.auth.RegisterRequest;
import com.predictmatch.userinfo.dto.auth.UserVerificationRequest;
import com.predictmatch.userinfo.repository.RoleRepository;
import com.predictmatch.userinfo.repository.UserInfoRepository;
import com.predictmatch.userinfo.repository.UserRepository;
import com.predictmatch.userinfo.security.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @Transactional @Slf4j
public class AuthServiceImpl implements AuthService, UserDetailsService {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    JwtUtils jwtUtils;


    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save( role );
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName,username);

        User user = userRepository.findByUsername( username );
        Role role = roleRepository.findByName( roleName );

        user.getRoles().add( role );
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}",username);

        return userRepository.findByUsername( username );
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<Boolean> verifyUsername(UserVerificationRequest verifyUser, String token) {
        DecodedJWT decodedJWT = jwtUtils.decodeJwtToken( token );

        String tokenUsername = decodedJWT.getSubject();
        String username= verifyUser.getUsername();

        if(!tokenUsername.equals( username )) {
            log.error( "Error: Unauthorized operation!" );
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body(false);
        }

        return ResponseEntity.status( HttpStatus.OK).body( true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername( username );
        if(user == null) {
            log.error( "User not found in the database" );
            throw new UsernameNotFoundException( "User not found in the database" );
        } else {
            log.info( "User found in the database: {}",username );

        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach( role -> {
            authorities.add(new SimpleGrantedAuthority( role.getName() ));
        } );

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                authorities);
    }

    @Override
    public ResponseEntity<?> registerUser(RegisterRequest registerRequest) {
        if(userInfoRepository.existsByUsername( registerRequest.getUsername())) {
                return ResponseEntity.badRequest().body( new MessageResponse("Error: Username: "+registerRequest.getUsername()+
                        " already exists!"));
            }

        if(userInfoRepository.existsByEmail( registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body( new MessageResponse("Error: Email: "+registerRequest.getEmail()+" " +
                    "already exists!"));
        }

        if(!registerRequest.getPassword().equals( registerRequest.getRepeatedPassword() )) {
            return ResponseEntity.badRequest().body( new MessageResponse("Passwords are not the same!"));
        }

        User user =  userRepository.save( new User(registerRequest.getUsername(),passwordEncoder.encode(registerRequest.getPassword()),
                new ArrayList<>()));


        Role role = roleRepository.findByName("ROLE_USER");

        addRoleToUser( user.getUsername(),role.getName());


        log.info("Saving new user {} to the database",user.getUsername());


        log.info("Creating new user profile for {} and save to the database",user.getUsername());

        UserInfoResponse userInfo =  userInfoService.createUserProfile(registerRequest,user);

        return new ResponseEntity<UserInfoResponse>(userInfo,
                HttpStatus.CREATED) ;
    }


}
