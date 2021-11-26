package com.predictmatch.liveresults.dto;

import com.predictmatch.liveresults.model.Team;

public class LeagueStandingDto {
    private Long rank;
    private Team team;
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
