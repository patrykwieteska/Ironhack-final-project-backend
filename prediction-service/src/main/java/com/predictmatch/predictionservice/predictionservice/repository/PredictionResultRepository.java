package com.predictmatch.predictionservice.predictionservice.repository;

import com.predictmatch.predictionservice.predictionservice.dao.PredictionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionResultRepository  extends JpaRepository<PredictionResult,Long> {
}
