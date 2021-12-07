package com.predictmatch.predictionservice.predictionservice.service;

import com.predictmatch.predictionservice.predictionservice.dao.Prediction;
import com.predictmatch.predictionservice.predictionservice.dao.PredictionResult;
import com.predictmatch.predictionservice.predictionservice.dto.*;
import com.predictmatch.predictionservice.predictionservice.dto.fixture.FixtureDto;
import com.predictmatch.predictionservice.predictionservice.dto.history.UserPredictionHistoryDto;
import com.predictmatch.predictionservice.predictionservice.enums.PredictionStatus;
import com.predictmatch.predictionservice.predictionservice.exceptions.PredictionOnLiveMatchException;
import com.predictmatch.predictionservice.predictionservice.mapper.PredictionMapper;
import com.predictmatch.predictionservice.predictionservice.repository.PredictionRepository;
import com.predictmatch.predictionservice.predictionservice.repository.PredictionResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service @Slf4j
public class PredictionServiceImpl implements PredictionService {

    @Autowired
    PredictionRepository predictionRepository;

    @Autowired
    PredictionResultRepository predictionResultRepository;

    @Autowired
    FixtureService fixtureService;

    @Override
    @Transactional
    public ResponseEntity<PredictionResponse> addNewPrediction(NewPredictionRequest newPredictionRequest) {

        // 1. make a PredictionEntity from predictionRequest
       FixtureDto fixture = fixtureService.getFixtureById( newPredictionRequest.getFixtureId() );

        Prediction prediction = PredictionMapper.predictionRequestToPrediction( newPredictionRequest,fixture,null);

        if(newPredictionRequest.getUsername()==null || newPredictionRequest.getUsername().equals( "" ))
            throw new NoSuchElementException("User id cannot be null or 0");


        if(ChronoUnit.MINUTES.between ( fixture.getDate() ,prediction.getPredictionDate())>0)
            throw new PredictionOnLiveMatchException( fixture.getFixtureId());

        PredictionResult predictionResult = new PredictionResult(
                0,
                PredictionStatus.PENDING
        );

        predictionResultRepository.save(predictionResult);
        prediction.setPredictionResult( predictionResult );
        predictionRepository.save( prediction );

        PredictionResponse predictionResponse = new PredictionResponse(PredictionMapper.predictionEntityToDto( prediction ),
                PredictionMapper.predictionResultEntityToDto( predictionResult ));

        return ResponseEntity.ok(predictionResponse);

    }

    @Override
    @Transactional
    public ResponseEntity<PredictionResponse> getUserPredictionByFixture(GetPredictionRequest predictionRequest) {

        // get fixture Result
        FixtureDto fixtureDto = fixtureService.getFixtureById(predictionRequest.getFixtureId());

        // check if fixture is existing
        if(fixtureDto==null)
            throw new EntityNotFoundException("Not found fixture with id: "+predictionRequest.getFixtureId());


        Optional<Prediction> storedPrediction =
                predictionRepository.findById( predictionRequest.getFixtureId()+"_"+ predictionRequest.getUsername());

        // check if there is prediction for the fixture
        if(storedPrediction.isEmpty())
            return ResponseEntity.ok( new PredictionResponse(null,new PredictionResultDto(0,PredictionStatus.NONE,
                    PredictionStatus.NONE.value )));


        PredictionResult predictionResult = storedPrediction.get().getPredictionResult();
        Prediction prediction= storedPrediction.get();

        // update prediction result for finished matches
        if(fixtureDto.getStatus().equals( "FINISHED") && predictionResult.getStatus().equals( PredictionStatus.PENDING )) {

            PredictionResult newResult = PredictionMapper.updatePredictionResult( prediction,
                    fixtureDto, predictionResult);

            predictionResultRepository.save( newResult );

            prediction.setPredictionResult( newResult );
            predictionRepository.save( prediction);

            return ResponseEntity.ok(new PredictionResponse(PredictionMapper.predictionEntityToDto( prediction ),
                    PredictionMapper.predictionResultEntityToDto( newResult )
                    ));
        }

        return ResponseEntity.ok (new PredictionResponse(PredictionMapper.predictionEntityToDto( prediction ),
                PredictionMapper.predictionResultEntityToDto( predictionResult )));

    }

    @Override
    public ResponseEntity<List<PredictionResponse>> getAllUserPredictions(String username) {

        List<Prediction> userPredictions = predictionRepository.findByUsername( username );
        List<PredictionResponse> predictionList = new ArrayList<>();

        if(userPredictions.size()==0)
            return ResponseEntity.ok(predictionList);

        userPredictions.forEach( prediction -> {

            predictionList.add( new PredictionResponse(PredictionMapper.predictionEntityToDto( prediction ),
                    PredictionMapper.predictionResultEntityToDto( prediction.getPredictionResult() )) );
        } );


        return ResponseEntity.ok(predictionList);
    }

    @Override
    public UserPredictionHistoryDto getUserPredictionHistory(String username) {


        List<IUserPredictionInfo> userPredictionInfoList = predictionResultRepository.getUserPredictionsByStatus( username );
        Integer totalPredictions = predictionResultRepository.getUserTotalPoints( username );

        log.info( "userPredictionInfoList {}",userPredictionInfoList );
        log.info( "totalPredictions {}",totalPredictions );

        if(userPredictionInfoList.size() == 0 || totalPredictions==0) {
            log.info( "new UserPredictionHistoryDto: {}",new UserPredictionHistoryDto(0,0,0,0) );
            return new UserPredictionHistoryDto(0,0,0,0);
        }


        return PredictionMapper.infoToPredictionHistory(userPredictionInfoList,totalPredictions);
    }
}
