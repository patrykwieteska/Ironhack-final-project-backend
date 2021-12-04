package com.predictmatch.edgeservice.dto.league;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LeagueDataDto {
    private Long id;
    private String name;
    private Integer season;
    private String logo;
    private String country;
    private String countryFlag;
    private List<LeagueTableDto> standings;
}
