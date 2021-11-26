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

    private Integer teamId;

    private Integer points;

    private Integer played;

    private Integer win;

    private Integer draw;

    private Integer lose;

    private Integer goalsFor;

    private Integer goalsAgainst;

    private Integer goalsDifference;

    private String rankStatus;

    private Long leagueId;
}
