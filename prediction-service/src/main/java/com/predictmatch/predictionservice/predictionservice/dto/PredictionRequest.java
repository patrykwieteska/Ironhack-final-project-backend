package com.predictmatch.predictionservice.predictionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PredictionRequest {
    private Long fixtureId;
    private Long roundId;
    private Long userId;
    private Integer predictedHomeGoals;
    private Integer predictedAwayGoals;
}
