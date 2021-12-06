package com.predictmatch.userinfo.service;

import com.predictmatch.userinfo.dao.auth.Role;
import com.predictmatch.userinfo.dao.auth.User;
import com.predictmatch.userinfo.dto.MessageResponse;
import com.predictmatch.userinfo.dto.UserInfoResponse;
import com.predictmatch.userinfo.dto.auth.RegisterRequest;
import com.predictmatch.userinfo.repository.RoleRepository;
import com.predictmatch.userinfo.repository.UserInfoRepository;
import com.predictmatch.userinfo.repository.UserRepository;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername( username );
        if(user == null) {
            log.error( "User not found in the database" );
            throw new UsernameNotFoundException( "User not found in the database" );
        } else {
            log.error( "User found in the database: {}",username );

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

        User user =  userRepository.save( new User(registerRequest.getUsername(),passwordEncoder.encode(registerRequest.getPassword()),
                new ArrayList<>()));


        Role role = roleRepository.findByName("ROLE_USER");

        addRoleToUser( user.getUsername(),role.getName());


        log.info("Saving new user {} to the database",user.getUsername());


        log.info("Creating new user profile for {} and save to the database",user.getUsername());

        UserInfoResponse userInfo =  userInfoService.createUserProfile(registerRequest,user);

        return new ResponseEntity<MessageResponse>(new MessageResponse("User "+registerRequest.getUsername()+" registered!"),
                HttpStatus.CREATED) ;
    }


}
