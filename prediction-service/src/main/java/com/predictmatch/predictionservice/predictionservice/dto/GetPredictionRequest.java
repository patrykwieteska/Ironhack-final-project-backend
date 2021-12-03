package com.predictmatch.predictionservice.predictionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPredictionRequest {
    Long userId;
    Long fixtureId;
}
