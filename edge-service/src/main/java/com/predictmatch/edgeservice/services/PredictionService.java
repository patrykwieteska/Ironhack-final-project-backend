package com.predictmatch.edgeservice.services;

import com.predictmatch.edgeservice.dto.fixture.FixtureDto;
import com.predictmatch.edgeservice.dto.fixture.FixtureResponseDto;
import com.predictmatch.edgeservice.dto.fixture.predicted.FixturePredictionDto;
import com.predictmatch.edgeservice.dto.fixture.predicted.UserRoundFixtureRequest;
import com.predictmatch.edgeservice.dto.prediction.GetPredictionRequest;
import com.predictmatch.edgeservice.dto.prediction.NewPredictionRequest;
import com.predictmatch.edgeservice.dto.prediction.PredictionResponse;
import com.predictmatch.edgeservice.dto.user.UserInfoResponse;
import com.predictmatch.edgeservice.proxy.LiveResultsProxy;
import com.predictmatch.edgeservice.proxy.PredictionProxy;
import com.predictmatch.edgeservice.proxy.UserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PredictionService {


    @Autowired
    PredictionProxy predictionProxy;

    @Autowired
    UserProxy userProxy;

    @Autowired
    LiveResultsProxy liveResultsProxy;

    public ResponseEntity<PredictionResponse> predictMatch(NewPredictionRequest predictionRequest) {

        Optional<UserInfoResponse> storedUser = Optional.ofNullable( userProxy.findUserById( predictionRequest.getUserId() ).getBody() );
        Optional<FixtureDto> storedFixture =
                Optional.ofNullable( liveResultsProxy.findFixtureById( predictionRequest.getFixtureId()).getBody());

        if(storedUser.isEmpty())
            throw new EntityNotFoundException("There is no user with id "+predictionRequest.getUserId());

        if(storedFixture.isEmpty())
            throw new EntityNotFoundException("There is no fixture with id "+predictionRequest.getFixtureId());

        return predictionProxy.predictMatch( predictionRequest);

    }

    public ResponseEntity<List<FixturePredictionDto>> getFixturesPredictionsByRoundId(UserRoundFixtureRequest userRoundFixtureRequest) {

        Optional<UserInfoResponse> userInfoResponse =
                Optional.ofNullable( userProxy.findUserById( userRoundFixtureRequest.getUserId() ).getBody());

        if(userInfoResponse.isEmpty())
            throw new EntityNotFoundException("Not found user with id: "+userRoundFixtureRequest.getUserId());


        Optional<FixtureResponseDto> fixtureResponseDto =
                Optional.ofNullable( liveResultsProxy.initAllFixtures( userRoundFixtureRequest.getRound()).getBody() );

        if(fixtureResponseDto.isEmpty())
            throw new EntityNotFoundException("Not found fixtures for round: "+userRoundFixtureRequest.getRound());

        if(fixtureResponseDto.get().getFixtures().size()==0) {
            return ResponseEntity.notFound().build();
        }



        List<FixturePredictionDto> fixturePredictionDtoList = new ArrayList<>();

        fixtureResponseDto.get().getFixtures().forEach( fixture -> {
            GetPredictionRequest predictionRequest = new GetPredictionRequest(userRoundFixtureRequest.getUserId(),
                    fixture.getFixtureId());

            PredictionResponse predictionResponse =
                    predictionProxy.getUserPredictionByFixture( predictionRequest ).getBody();

            FixturePredictionDto fixturePredictionDto = new FixturePredictionDto(
                    fixture,
                    predictionResponse
            );

            fixturePredictionDtoList.add( fixturePredictionDto );
        } );



        return ResponseEntity.ok(fixturePredictionDtoList);


    }
}
