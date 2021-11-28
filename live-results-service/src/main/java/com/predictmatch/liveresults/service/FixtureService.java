package com.predictmatch.liveresults.service;

import com.predictmatch.liveresults.dto.FixtureDto;
import com.predictmatch.liveresults.enmus.FixtureStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FixtureService {

    ResponseEntity<List<FixtureDto>> initAllFixtures();

    ResponseEntity<List<FixtureDto>> initFixturesByStatus(FixtureStatus fixtureStatus);
}
