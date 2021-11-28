package com.predictmatch.liveresults.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeagueStandingDto {
    private Long rank;
    private TeamDto team;
    private Integer points;
    private Integer played;
    private Integer win;
    private Integer draw;
    private Integer lose;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer goalsDifference;
    private String rankStatus;
}
