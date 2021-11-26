package com.predictmatch.liveresults.controller;

import com.predictmatch.liveresults.dto.TeamDto;
import com.predictmatch.liveresults.model.Team;
import com.predictmatch.liveresults.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/predictmatch/api/v1/teams")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeamById() {
        return teamService.getTeamById();
    }
}
