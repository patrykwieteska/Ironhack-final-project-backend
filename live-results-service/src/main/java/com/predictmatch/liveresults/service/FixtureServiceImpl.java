package com.predictmatch.liveresults.service;

import com.predictmatch.liveresults.dto.FixtureDto;
import com.predictmatch.liveresults.model.Fixture;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FixtureServiceImpl implements FixtureService {

    @Override
    public ResponseEntity<FixtureDto> initAllMatches() {
        return null;
    }

    @Override
    public ResponseEntity<FixtureDto> initAllFixtures() {
        return null;
    }

    @Override
    public ResponseEntity<FixtureDto> initPostponedFixtures() {
        return null;
    }

    @Override
    public ResponseEntity<FixtureDto> initUpcomingFixtures() {
        return null;
    }

    @Override
    public ResponseEntity<FixtureDto> initFinishedFixtures() {
        return null;
    }

    @Override
    public ResponseEntity<FixtureDto> initInProgressFixtures() {
        return null;
    }
}
