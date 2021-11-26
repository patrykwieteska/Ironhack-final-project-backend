package com.predictmatch.liveresults.dto;

import com.predictmatch.liveresults.model.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FixtureDto {

    private Long externalFixtureId;
    private LocalDateTime date;
    private String timezone;
    private String referee;
    private String venueName;
    private String venueCity;
    private String status;
    private Team homeTeam;
    private Team awayTeam;
    private Boolean homeIsWinner;
    private Boolean awayIsWinner;
    private Integer homeHalfTimeGoals;
    private Integer awayHalfTimeGoals;
    private Integer homeFullTimeGoals;
    private Integer awayFullTimeGoals;
    private Long leagueId;

}
