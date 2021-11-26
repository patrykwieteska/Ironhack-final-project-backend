package com.predictmatch.liveresults.service;

import com.predictmatch.liveresults.dto.TeamDto;
import org.springframework.http.ResponseEntity;

public interface TeamService {
    ResponseEntity<TeamDto> getTeamById();
}
