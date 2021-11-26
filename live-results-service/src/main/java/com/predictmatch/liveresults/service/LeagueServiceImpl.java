package com.predictmatch.liveresults.service;

import com.predictmatch.liveresults.dto.LeagueDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LeagueServiceImpl implements LeagueService {
    @Override
    public ResponseEntity<LeagueDto> initLeagueData() {
        return null;
    }
}
