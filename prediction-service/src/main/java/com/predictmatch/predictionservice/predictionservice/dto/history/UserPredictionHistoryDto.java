package com.predictmatch.predictionservice.predictionservice.dto.history;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPredictionHistoryDto {

    Integer exactPredictions;
    Integer correctPredictions;
    Integer totalPredictions;
    Integer totalPoints;

}