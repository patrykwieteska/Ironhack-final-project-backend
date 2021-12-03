package com.predictmatch.predictionservice.predictionservice.dao;

import com.predictmatch.predictionservice.predictionservice.enums.PredictionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PredictionResult {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int points;
    @Enumerated(EnumType.STRING)
    private PredictionStatus status;


    public PredictionResult(int points, PredictionStatus status) {
        this.points = points;
        this.status = status;
    }
}
