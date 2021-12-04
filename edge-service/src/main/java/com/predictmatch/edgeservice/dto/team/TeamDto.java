package com.predictmatch.edgeservice.dto.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TeamDto {

    private Long id;
    private String name;
    private String form;
    private String logo;
    private Long leagueId;

}
