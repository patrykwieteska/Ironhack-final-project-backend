package com.predictmatch.predictionservice.predictionservice.repository;

import com.predictmatch.predictionservice.predictionservice.dao.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction,Long> {
}
