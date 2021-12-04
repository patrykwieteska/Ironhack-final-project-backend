package com.predictmatch.predictionservice.predictionservice.controller;

import com.predictmatch.predictionservice.predictionservice.dto.GetPredictionRequest;
import com.predictmatch.predictionservice.predictionservice.dto.PredictionDto;
import com.predictmatch.predictionservice.predictionservice.dto.PredictionRequest;
import com.predictmatch.predictionservice.predictionservice.dto.history.UserPredictionHistoryDto;
import com.predictmatch.predictionservice.predictionservice.dto.PredictionResponse;
import com.predictmatch.predictionservice.predictionservice.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/predictions")
public class PredictionController {


    @Autowired
    PredictionService predictionService;

    @PostMapping("/new")
    ResponseEntity<PredictionDto> predictMatch(@RequestBody PredictionRequest predictionRequest) {
        return predictionService.addNewPrediction(predictionRequest);
    }

    @PostMapping
    ResponseEntity<PredictionResponse> getUserPredictionByFixture(@RequestBody @Valid GetPredictionRequest predictionRequest) {
        return predictionService.getUserPredictionByFixture(predictionRequest);
    }

    @GetMapping("user/{userId}")
    ResponseEntity<List<PredictionResponse>> getAllUserPredictions(@PathVariable(name="userId") Long id) {
        return predictionService.getAllUserPredictions( id );
    }

    @GetMapping("/user/history/{userId}")
    ResponseEntity<UserPredictionHistoryDto> getUserPredictionsHistory(@PathVariable(name="userId") Long id) {
        return predictionService.getUserPredictionHistory( id );
    }


}
