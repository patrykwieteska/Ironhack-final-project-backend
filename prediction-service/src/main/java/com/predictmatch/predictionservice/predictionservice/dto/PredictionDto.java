package com.predictmatch.predictionservice.predictionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PredictionDto {
    private String id;
    private Long fixtureId;
    private int roundId;
    private Long userId;
    private Integer predictedHomeGoals;
    private Integer predictedAwayGoals;
    private boolean predictedHomeWinner;
    private boolean predictedAwayWinner;
    private LocalDateTime predictionDate;

}
