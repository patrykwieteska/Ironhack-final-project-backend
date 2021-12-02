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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fixtureId;
    private Long roundId;
    private Long userId;
    private Integer predictedHomeGoals;
    private Integer predictedAwayGoals;
    private boolean predictedHomeWinner;
    private boolean predictedAwayWinner;
    private LocalDateTime predictionDate;
    @OneToOne
    @JoinColumn(name = "prediction_result_id")
    private PredictionResult predictionResult;

}
