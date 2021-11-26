package com.predictmatch.liveresults.service;

import com.predictmatch.liveresults.dto.FixtureDto;
import org.springframework.http.ResponseEntity;

public interface FixtureService {
    ResponseEntity<FixtureDto> initAllMatches();
}
