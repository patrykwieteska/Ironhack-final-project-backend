package com.predictmatch.predictionservice.predictionservice.controller;

import com.predictmatch.predictionservice.predictionservice.dto.GetPredictionRequest;
import com.predictmatch.predictionservice.predictionservice.dto.NewPredictionRequest;
import com.predictmatch.predictionservice.predictionservice.dto.PredictionResponse;
import com.predictmatch.predictionservice.predictionservice.dto.history.UserPredictionHistoryDto;
import com.predictmatch.predictionservice.predictionservice.service.PredictionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/predictions")
public class PredictionController {

    private static final Logger logger = LoggerFactory.getLogger( PredictionController.class );

    @Autowired
    PredictionService predictionService;

    @PostMapping("/new")
    ResponseEntity<PredictionResponse> predictMatch(@RequestBody NewPredictionRequest newPredictionRequest) {

        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName() +  " requestBody "+ newPredictionRequest.toString() );

        return predictionService.addNewPrediction( newPredictionRequest );
    }

    @PostMapping
    ResponseEntity<PredictionResponse> getUserPredictionByFixture(@RequestBody @Valid GetPredictionRequest predictionRequest) {
        return predictionService.getUserPredictionByFixture(predictionRequest);
    }

    @GetMapping("user/{username}")
    ResponseEntity<List<PredictionResponse>> getAllUserPredictions(@PathVariable(name="username") String username) {
        return predictionService.getAllUserPredictions( username );
    }

    @GetMapping("/user/history/{username}")
    UserPredictionHistoryDto getUserPredictionsHistory(@PathVariable(name="username") String username) {
        return predictionService.getUserPredictionHistory( username );
    }


}
