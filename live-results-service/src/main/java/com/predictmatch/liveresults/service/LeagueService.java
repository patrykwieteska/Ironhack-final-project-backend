package com.predictmatch.liveresults.service;

import com.predictmatch.liveresults.dto.LeagueDto;
import org.springframework.http.ResponseEntity;

public interface LeagueService {

    ResponseEntity<LeagueDto> initLeagueData();

    Integer getLastPlayedRound();
}
