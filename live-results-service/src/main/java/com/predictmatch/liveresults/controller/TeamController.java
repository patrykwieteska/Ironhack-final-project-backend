package com.predictmatch.liveresults.controller;

import com.predictmatch.liveresults.dto.TeamDto;
import com.predictmatch.liveresults.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/liveresults/api/v1/teams")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable(name="teamId") Long teamId) {
        return teamService.getTeamById(teamId);
    }

    @GetMapping
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        return teamService.getAllTeams();
    }


}
