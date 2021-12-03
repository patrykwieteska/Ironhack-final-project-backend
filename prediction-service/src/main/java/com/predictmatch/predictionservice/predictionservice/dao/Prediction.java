package com.predictmatch.predictionservice.predictionservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prediction {
    @Id
    @Column(name = "id", nullable = false)
    private String predictionId;
    private Long fixtureId;
    private int roundId;
    private Long userId;
    private Integer predictedHomeGoals;
    private Integer predictedAwayGoals;
    private boolean predictedHomeWinner;
    private boolean predictedAwayWinner;
    private LocalDateTime predictionDate;
    @OneToOne
    @JoinColumn(name = "prediction_result_id")
    private PredictionResult predictionResult;


    public Prediction(Long fixtureId, int roundId, Long userId, Integer predictedHomeGoals, Integer predictedAwayGoals,
                      boolean predictedHomeWinner, boolean predictedAwayWinner, LocalDateTime predictionDate, PredictionResult predictionResult) {
        this.fixtureId = fixtureId;
        this.roundId = roundId;
        this.userId = userId;
        this.predictedHomeGoals = predictedHomeGoals;
        this.predictedAwayGoals = predictedAwayGoals;
        this.predictedHomeWinner = predictedHomeWinner;
        this.predictedAwayWinner = predictedAwayWinner;
        this.predictionDate = predictionDate;
        this.predictionResult = predictionResult;
    }
}
