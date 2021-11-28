package com.predictmatch.liveresults.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fixture {

    @Id
    private Long externalFixtureId;

    private LocalDateTime date;

    private String timezone;

    private String referee;

    private String venueName;

    private String venueCity;

    private String status;

    private Long homeTeamId;

    private Long awayTeamId;

    private Boolean homeIsWinner;

    private Boolean awayIsWinner;

    private Integer homeHalfTimeGoals;

    private  Integer awayHalfTimeGoals;

    private Integer homeFullTimeGoals;

    private Integer awayFullTimeGoals;

    private Long leagueId;



}
