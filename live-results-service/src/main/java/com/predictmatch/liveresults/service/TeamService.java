package com.predictmatch.liveresults.service;

import com.predictmatch.liveresults.dto.TeamDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeamService {
    ResponseEntity<TeamDto> getTeamById(Long id);

    ResponseEntity<List<TeamDto>> getAllTeams();
}
