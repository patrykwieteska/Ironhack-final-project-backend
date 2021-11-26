package com.predictmatch.liveresults.service;

import com.predictmatch.liveresults.dto.FixtureDto;
import org.springframework.http.ResponseEntity;

public interface FixtureService {
    ResponseEntity<FixtureDto> initAllMatches();

    ResponseEntity<FixtureDto> initAllFixtures();

    ResponseEntity<FixtureDto> initPostponedFixtures();

    ResponseEntity<FixtureDto> initUpcomingFixtures();

    ResponseEntity<FixtureDto> initFinishedFixtures();

    ResponseEntity<FixtureDto> initInProgressFixtures();
}
