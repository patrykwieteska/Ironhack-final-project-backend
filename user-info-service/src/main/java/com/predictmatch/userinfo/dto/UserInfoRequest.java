package com.predictmatch.userinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserInfoRequest {
    private String username;
    private String city;
    private String country;
    private String info;
    @Email
    private String email;
    private Long teamId;
}
