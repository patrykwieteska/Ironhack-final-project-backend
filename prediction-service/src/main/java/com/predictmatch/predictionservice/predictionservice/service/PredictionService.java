package com.predictmatch.predictionservice.predictionservice.service;

import com.predictmatch.predictionservice.predictionservice.dto.GetPredictionRequest;
import com.predictmatch.predictionservice.predictionservice.dto.NewPredictionRequest;
import com.predictmatch.predictionservice.predictionservice.dto.PredictionResponse;
import com.predictmatch.predictionservice.predictionservice.dto.history.UserPredictionHistoryDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PredictionService {
    ResponseEntity<PredictionResponse> addNewPrediction(NewPredictionRequest newPredictionRequest);

    ResponseEntity<PredictionResponse> getUserPredictionByFixture(GetPredictionRequest predictionRequest);

    ResponseEntity<List<PredictionResponse>> getAllUserPredictions(Long id);

    ResponseEntity<UserPredictionHistoryDto> getUserPredictionHistory(Long id);
}
