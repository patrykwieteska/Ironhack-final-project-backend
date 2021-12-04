package com.predictmatch.predictionservice.predictionservice.mapper;

import com.predictmatch.predictionservice.predictionservice.dao.Prediction;
import com.predictmatch.predictionservice.predictionservice.dao.PredictionResult;
import com.predictmatch.predictionservice.predictionservice.dto.PredictionDto;
import com.predictmatch.predictionservice.predictionservice.dto.PredictionRequest;
import com.predictmatch.predictionservice.predictionservice.dto.PredictionResultDto;
import com.predictmatch.predictionservice.predictionservice.dto.fixture.FixtureDto;
import com.predictmatch.predictionservice.predictionservice.dto.history.UserPredictionHistoryDto;
import com.predictmatch.predictionservice.predictionservice.enums.PredictionStatus;
import com.predictmatch.predictionservice.predictionservice.service.IUserPredictionInfo;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

public class PredictionMapper {

    public static Prediction predictionRequestToPrediction(PredictionRequest predictionRequest, FixtureDto fixtureDto
            , PredictionResult predictionResult) {
        LocalDateTime predictionDate = LocalDateTime.now( ZoneOffset.UTC);
        boolean homeTeamWinner = predictionRequest.getPredictedHomeGoals() > predictionRequest.getPredictedAwayGoals();
        boolean awayTeamWinner = predictionRequest.getPredictedAwayGoals() > predictionRequest.getPredictedHomeGoals();

        return new Prediction(
                predictionRequest.getFixtureId()+"_"+ predictionRequest.getUserId(),
                fixtureDto.getFixtureId(),
                fixtureDto.getRound(),
                predictionRequest.getUserId(),
                predictionRequest.getPredictedHomeGoals(),
                predictionRequest.getPredictedAwayGoals(),
                homeTeamWinner,
                awayTeamWinner,
                predictionDate,
                predictionResult
        );
    }


    public static PredictionDto predictionEntityToDto(Prediction prediction) {

        return new PredictionDto(
                prediction.getPredictionId(),
                prediction.getFixtureId(),
                prediction.getRoundId(),
                prediction.getUserId(),
                prediction.getPredictedHomeGoals(),
                prediction.getPredictedAwayGoals(),
                prediction.isPredictedHomeWinner(),
                prediction.isPredictedAwayWinner(),
                prediction.getPredictionDate()
                );
    }




    public static PredictionResult updatePredictionResult(Prediction prediction, FixtureDto fixtureDto,
                                                          PredictionResult oldPredictionResult) {

        PredictionStatus predictionStatus = PredictionStatus.INCORRECT;
        int points = 0;

        // check if user predict exact score
        if(Objects.equals( fixtureDto.getHomeGoals(), prediction.getPredictedHomeGoals() )
                && Objects.equals( fixtureDto.getAwayGoals(), prediction.getPredictedAwayGoals() )) {
            predictionStatus = PredictionStatus.EXACT;
            points = 3;
        } else if(prediction.isPredictedHomeWinner()==
                fixtureDto.getHomeIsWinner()
                && prediction.isPredictedAwayWinner()
                == fixtureDto.getAwayIsWinner()) {
            predictionStatus = PredictionStatus.CORRECT;
            points = 1;

        }

        return new PredictionResult(oldPredictionResult.getId(),points,predictionStatus);
    }

    public static PredictionResultDto predictionResultEntityToDto(PredictionResult predictionResult) {
        return new PredictionResultDto(
                predictionResult.getPoints(),
                predictionResult.getStatus(),
                predictionResult.getStatus().value);
    }


    public static UserPredictionHistoryDto infoToPredictionHistory(
                                                                   List<IUserPredictionInfo> userPredictionInfoList,
                                                                   Integer totalPredictions) {

        Integer exactResults = 0;
        Integer correctResults = 0;
        int totalPoints = 0;


        for (IUserPredictionInfo iUserPredictionInfo : userPredictionInfoList) {
            switch (iUserPredictionInfo.getStatus()) {
                case "EXACT" -> {
                    exactResults++;
                    totalPoints += 3;
                }
                case "CORRECT" -> {
                    correctResults++;
                    totalPoints += 1;
                }
            }
        }

        return new UserPredictionHistoryDto(exactResults,correctResults,totalPredictions,totalPoints);
    }
}
