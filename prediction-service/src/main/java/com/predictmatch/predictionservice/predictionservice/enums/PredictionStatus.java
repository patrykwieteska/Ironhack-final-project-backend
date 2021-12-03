package com.predictmatch.predictionservice.predictionservice.enums;

public enum PredictionStatus {
    EXACT("Exact score"),
    CORRECT("Correct result"),
    INCORRECT("Incorrect prediction"),
    NONE("No prediction for this match"),
    PENDING("Waiting for match to finish...");

    public final String value;

    PredictionStatus(String value) {
        this.value=value;
    }
}
