package com.predictmatch.liveresults.model;

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

    private int homeTeamId;

    private int awayTeamId;

    private int homeHalfTimeGoals;

    private  int awayHalfTimeGoals;

    private int homeFullTimeGoals;

    private int awayFullTimeGoals;

    @ManyToOne
    @JoinColumn(name = "league_external_league_id")
    private League league;



}
