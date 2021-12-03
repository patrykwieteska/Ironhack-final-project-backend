package com.predictmatch.predictionservice.predictionservice.dto.fixture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {

    private Long id;
    private String name;
    private String form;
    private String logo;
    private Long leagueId;

}
