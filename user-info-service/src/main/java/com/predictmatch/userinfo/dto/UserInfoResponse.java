package com.predictmatch.userinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserInfoResponse {
    private Long userId;
    private String username;
    private String city;
    private String country;
    private String info;
    private String email;
    private TeamDto team;

}
