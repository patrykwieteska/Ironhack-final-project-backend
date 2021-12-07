package com.predictmatch.userinfo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.predictmatch.userinfo.dao.UserInfo;
import com.predictmatch.userinfo.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.stream.Collectors;

@Component //changed from service
public class JwtUtils {

    private final Logger logger = LoggerFactory.getLogger( JwtUtils.class );

    @Value("${jwt.token.secret.value}")
    private String jwtSecret;

    @Autowired
    UserInfoRepository userInfoRepository;

    public DecodedJWT decodeJwtToken(String authorizationHeader) {
        String token = authorizationHeader.substring( 7 );
        Algorithm algorithm = Algorithm.HMAC256( jwtSecret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();

        return verifier.verify( token );
    }

    public String generateJwtToken(Authentication authentication, HttpServletRequest request) {
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256( jwtSecret.getBytes()); // jwtSecret should to be SECRET

        UserInfo userInfo = userInfoRepository.findByUsername( user.getUsername());

        long currentTimeMillis = System.currentTimeMillis();
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(  new Date(currentTimeMillis))
                .withExpiresAt( new Date(currentTimeMillis +100 * 60 * 1000)) // token expires after 10 minutes
                .withIssuer( request.getRequestURI().toString())
                .withClaim( "roles",user.getAuthorities().stream().map( GrantedAuthority::getAuthority ).collect( Collectors.toList()))
                .withClaim( "user_id", userInfo.getId())
                .withClaim( "email", userInfo.getEmail())
                .sign( algorithm );
    }
}
