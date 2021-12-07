package com.predictmatch.edgeservice.controller;

import com.predictmatch.edgeservice.dto.fixture.predicted.FixturePredictionDto;
import com.predictmatch.edgeservice.dto.fixture.predicted.UserRoundFixtureRequest;
import com.predictmatch.edgeservice.dto.prediction.NewPredictionRequest;
import com.predictmatch.edgeservice.dto.prediction.PredictionResponse;
import com.predictmatch.edgeservice.services.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/predictions")
public class PredictionController {

    @Autowired
    PredictionService predictionService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<PredictionResponse> predictMatch(@RequestBody NewPredictionRequest predictionRequest,@RequestHeader("Authorization") String token) {
        return predictionService.predictMatch( predictionRequest,token );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<FixturePredictionDto>> initFixturePredictionsByRoundId(@RequestBody @Valid UserRoundFixtureRequest userRoundFixtureRequest,
                                                                               @RequestHeader("Authorization") String token) {
        return predictionService.getFixturesPredictionsByRoundId(userRoundFixtureRequest,token);
    }
}
