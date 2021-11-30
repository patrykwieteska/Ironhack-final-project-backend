package com.predictmatch.liveresults.dao;

import com.predictmatch.liveresults.enmus.FixtureStatus;
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

    @Enumerated(EnumType.STRING)
    private FixtureStatus status;

    private Long homeTeamId;

    private Long awayTeamId;

    private Boolean homeIsWinner;

    private Boolean awayIsWinner;

    private Integer homeGoals;

    private Integer awayGoals;

    private int round;

    private Long leagueId;



}
