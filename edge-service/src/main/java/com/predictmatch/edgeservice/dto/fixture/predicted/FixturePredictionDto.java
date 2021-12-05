package com.predictmatch.edgeservice.dto.fixture.predicted;

import com.predictmatch.edgeservice.dto.fixture.FixtureDto;
import com.predictmatch.edgeservice.dto.prediction.PredictionResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FixturePredictionDto {
    FixtureDto fixture;
    PredictionResponse predictionInfo;
}
