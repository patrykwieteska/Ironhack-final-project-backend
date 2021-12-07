package com.predictmatch.edgeservice.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterRequest {

    private String username;
    private String password;
    private String repeatedPassword;
    private String city;
    private String country;
    private String info;
    @Email
    private String email;
    private Long teamId;

}
