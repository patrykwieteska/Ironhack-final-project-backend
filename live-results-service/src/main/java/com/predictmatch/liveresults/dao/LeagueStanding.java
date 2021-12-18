package com.predictmatch.liveresults.dao;

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
@Table(name = "standings")
public class LeagueStanding {
    @Id
    @Column(name = "rank_id", nullable = false)
    private Long rank;

    private Long teamId;

    private Integer points;

    private Integer played;

    private Integer win;

    private Integer draw;

    private Integer lose;

    private Integer goalsFor;

    private Integer goalsAgainst;

    private Integer goalsDifference;

    private String rankStatus;

    @JoinColumn(name="league_id", nullable = false, updatable = false, insertable = true)
    @ManyToOne
    private League leagueId;

}
