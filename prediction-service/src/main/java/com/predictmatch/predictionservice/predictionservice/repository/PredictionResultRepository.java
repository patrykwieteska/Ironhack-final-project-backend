package com.predictmatch.predictionservice.predictionservice.repository;

import com.predictmatch.predictionservice.predictionservice.dao.PredictionResult;
import com.predictmatch.predictionservice.predictionservice.service.IUserPredictionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionResultRepository  extends JpaRepository<PredictionResult,Long> {

    @Query(value = "select pr.status, sum(points) from prediction_result pr left join prediction p on p" +
            ".prediction_result_id = pr.id where p.username=:username group by pr.status",nativeQuery = true)
    List<IUserPredictionInfo> getUserPredictionsByStatus(String username);

    @Query(value="select count(*) from prediction_result pr left join prediction p on p.prediction_result_id=pr.id " +
            "where p.username=:username", nativeQuery = true)
    Integer getUserTotalPoints(String username);
}
