package com.predictmatch.predictionservice.predictionservice.controller;

import com.predictmatch.predictionservice.predictionservice.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/predictions/api/v1")
public class PredictionController {


    @Autowired
    PredictionService predictionService;

}
