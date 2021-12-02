package com.predictmatch.liveresults.dto;

import com.predictmatch.liveresults.dao.Team;
import com.predictmatch.liveresults.enmus.FixtureStatus;
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

    private Long fixtureId;
    private Long leagueId;
    private int round;
    private LocalDateTime date;
    private String timezone;
    private String referee;
    private String venueName;
    private String venueCity;
    private FixtureStatus status;
    private Integer elapsedTime;
    private TeamDto homeTeam;
    private TeamDto awayTeam;
    private Boolean homeIsWinner;
    private Boolean awayIsWinner;
    private Integer homeGoals;
    private Integer awayGoals;

}
