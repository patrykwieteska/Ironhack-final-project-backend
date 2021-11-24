package com.predictmatch.liveresults.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeagueStanding {

    @Id
    private Long rank;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private int position;

    private int points;

    private int played;

    private int win;

    private int draw;

    private int lose;

    private int goalsFor;

    private int goalsAgainst;

    private int goalsDifference;

    private int rankStatus;

    @ManyToOne
    @JoinColumn(name = "league_external_league_id")
    private League league;
}
