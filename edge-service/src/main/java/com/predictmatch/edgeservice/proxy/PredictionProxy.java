package com.predictmatch.edgeservice.proxy;

import com.predictmatch.edgeservice.dto.prediction.GetPredictionRequest;
import com.predictmatch.edgeservice.dto.prediction.NewPredictionRequest;
import com.predictmatch.edgeservice.dto.prediction.PredictionResponse;
import com.predictmatch.edgeservice.dto.prediction.UserPredictionHistoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient("prediction-service")
public interface PredictionProxy {

    @PostMapping("/api/v1/predictions/new")
    ResponseEntity<PredictionResponse> predictMatch(@RequestBody NewPredictionRequest predictionRequest);

    @PostMapping("/api/v1/predictions")
    ResponseEntity<PredictionResponse> getUserPredictionByFixture(@RequestBody @Valid GetPredictionRequest predictionRequest);

    @GetMapping("/api/v1/predictions/user/{userId}")
    ResponseEntity<List<PredictionResponse>> getAllUserPredictions(@PathVariable(name="userId") Long id);

    @GetMapping("/api/v1/predictions/user/history/{userId}")
    ResponseEntity<UserPredictionHistoryDto> getUserPredictionsHistory(@PathVariable(name="userId") Long id);
}
