package com.predictmatch.userinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserInfoRequest {
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String info;
    private String email;
    private int favouriteTeamId;
}
