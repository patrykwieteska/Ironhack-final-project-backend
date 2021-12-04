package com.predictmatch.edgeservice.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserInfoRequest {
    private String username;
    private String city;
    private String country;
    private String info;
    private String email;
    private long teamId;
}
