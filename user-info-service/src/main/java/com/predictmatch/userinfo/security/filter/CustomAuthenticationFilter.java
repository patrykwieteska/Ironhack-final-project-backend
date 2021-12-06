package com.predictmatch.userinfo.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.predictmatch.userinfo.dto.auth.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String secret ="ASJDASDANCZOSEMVOPERFAWLEKN34F93J94FMP";


    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            BufferedReader reader = request.getReader();
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while((line=reader.readLine())!=null) {
                stringBuilder.append( line );
            }

            LoginRequest authRequest = objectMapper.readValue( stringBuilder.toString(), LoginRequest.class );
//
//       String username = request.getParameter( "username" );
//       String password = request.getParameter( "password" );

            String username = authRequest.getUsername();
            String password = authRequest.getPassword();
            log.info("Username is: {}",username);
            log.info("Password is: {}",password);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( username,
                    password );


            return authenticationManager.authenticate( authenticationToken );
        } catch (Exception e) {
            log.info( "Error during authentication: {}",e.getMessage() );
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {
        //we will give to user the access token

        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256( secret.getBytes()); // secret shoukld to be replaced with real
        // SECRET value

        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt( new Date(System.currentTimeMillis() +10 * 60 * 1000))
                .withIssuer( request.getRequestURI().toString())
                .withClaim( "roles",user.getAuthorities().stream().map( GrantedAuthority::getAuthority ).collect( Collectors.toList()))
                .sign( algorithm );

        Map<String,String> tokens = new HashMap<>();
        tokens.put("accessToken",accessToken  );

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue( response.getOutputStream(),tokens );
    }


}
