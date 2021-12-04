package com.predictmatch.edgeservice.dto.fixture;

import com.predictmatch.edgeservice.dto.team.TeamDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FixtureDto {
    private Long fixtureId;
    private Long leagueId;
    private int round;
    private LocalDateTime date;
    private String timezone;
    private String referee;
    private String venueName;
    private String venueCity;
    private String status;
    private Integer elapsedTime;
    private TeamDto homeTeam;
    private TeamDto awayTeam;
    private Boolean homeIsWinner;
    private Boolean awayIsWinner;
    private Integer homeGoals;
    private Integer awayGoals;

}
