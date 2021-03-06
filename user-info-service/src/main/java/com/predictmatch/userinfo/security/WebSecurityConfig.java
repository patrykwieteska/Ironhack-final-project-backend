package com.predictmatch.userinfo.security;

import com.predictmatch.userinfo.security.filter.CustomAuthenticationFilter;
import com.predictmatch.userinfo.security.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder  );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter =
                new CustomAuthenticationFilter( authenticationManagerBean(),jwtUtils);

        customAuthenticationFilter.setFilterProcessesUrl( "/userinfo/api/v1/login" );

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );
        http.authorizeRequests().antMatchers( GET,"/userinfo/api/v1/users/**" ).permitAll();
        http.authorizeRequests().antMatchers( POST,"/userinfo/api/v1/login/**","/userinfo/api/v1/register/**" ).permitAll();
        http.authorizeRequests().antMatchers( POST,"http://user-info-service/userinfo/api/v1/login/**" ).permitAll();
        http.authorizeRequests().antMatchers("/userinfo/api/v1/users/**").hasAnyAuthority( "ROLE_USER");
        http.authorizeRequests().antMatchers(POST,"/userinfo/api/v1/verify/**").hasAnyAuthority( "ROLE_USER");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter( customAuthenticationFilter);
        http.addFilterBefore( new CustomAuthorizationFilter(jwtUtils), UsernamePasswordAuthenticationFilter.class );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
