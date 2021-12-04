package com.predictmatch.edgeservice.dto.fixture;

import com.predictmatch.edgeservice.dto.prediction.PredictionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FixtureResponseDto {
    String message;
    int results;
    List<FixtureDto> fixtures;
    List<PredictionDto> predictions;
}
