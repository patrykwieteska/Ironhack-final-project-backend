package com.predictmatch.predictionservice.predictionservice.dto;

import com.predictmatch.predictionservice.predictionservice.enums.PredictionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PredictionResultDto {

    private Integer points;
    private PredictionStatus status;
    private String message;

}


