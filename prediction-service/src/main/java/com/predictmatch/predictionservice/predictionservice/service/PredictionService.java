package com.predictmatch.predictionservice.predictionservice.service;

import com.predictmatch.predictionservice.predictionservice.dto.PredictionDto;
import com.predictmatch.predictionservice.predictionservice.dto.history.UserPredictionHistoryDto;
import com.predictmatch.predictionservice.predictionservice.dto.GetPredictionRequest;
import com.predictmatch.predictionservice.predictionservice.dto.PredictionRequest;
import com.predictmatch.predictionservice.predictionservice.dto.PredictionResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PredictionService {
    ResponseEntity<PredictionDto> addNewPrediction(PredictionRequest predictionRequest);

    ResponseEntity<PredictionResponse> getUserPredictionByFixture(GetPredictionRequest predictionRequest);

    ResponseEntity<List<PredictionResponse>> getAllUserPredictions(Long id);

    ResponseEntity<UserPredictionHistoryDto> getUserPredictionHistory(Long id);
}
