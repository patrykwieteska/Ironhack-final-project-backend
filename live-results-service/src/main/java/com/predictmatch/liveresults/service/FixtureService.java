package com.predictmatch.liveresults.service;

import com.predictmatch.liveresults.dto.FixtureResponseDto;
import com.predictmatch.liveresults.enmus.FixtureStatus;
import org.springframework.http.ResponseEntity;

public interface FixtureService {

    ResponseEntity<FixtureResponseDto> initAllFixtures();

    ResponseEntity<FixtureResponseDto> initFixturesByStatus(FixtureStatus fixtureStatus);
}
