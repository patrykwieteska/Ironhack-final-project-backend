package com.predictmatch.edgeservice.dto.league;

import com.predictmatch.edgeservice.dto.team.TeamDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LeagueTableDto {
    private Long rank;
    private TeamDto team;
    private Integer points;
    private Integer played;
    private Integer win;
    private Integer draw;
    private Integer lose;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer goalsDifference;
    private String rankStatus;
}
