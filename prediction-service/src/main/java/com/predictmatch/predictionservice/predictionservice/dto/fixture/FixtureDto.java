package com.predictmatch.predictionservice.predictionservice.dto.fixture;

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
    private Integer round;
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