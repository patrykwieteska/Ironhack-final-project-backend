package com.predictmatch.edgeservice.dto.prediction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPredictionRequest {
    private Long fixtureId;
    private String username;
    private Integer predictedHomeGoals;
    private Integer predictedAwayGoals;
}
