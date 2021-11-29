package com.predictmatch.userinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ExternalTeamDto {
    private Long id;
    private String name;
    private String form;
    private String logo;
    private Long leagueId;
}
