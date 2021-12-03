package com.predictmatch.predictionservice.predictionservice.exceptions;

public class PredictionOnLiveMatchException extends RuntimeException{
    public PredictionOnLiveMatchException(Long id ) {
        super("Cannot make a prediction! Match with id: "+ id + " has already started!");
    }
}
