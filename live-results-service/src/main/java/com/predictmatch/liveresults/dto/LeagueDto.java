package com.predictmatch.liveresults.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeagueDto {

    private Long id;
    private String name;
    private Integer season;
    private String logo;
    private String country;
    private String countryFlag;
    private List<LeagueStandingDto> standings;

}
