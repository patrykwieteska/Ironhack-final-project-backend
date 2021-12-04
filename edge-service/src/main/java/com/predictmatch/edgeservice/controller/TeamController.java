package com.predictmatch.edgeservice.controller;

import com.predictmatch.edgeservice.dto.team.TeamDto;
import com.predictmatch.edgeservice.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController
{
    @Autowired
    TeamService teamService;

    @GetMapping
    ResponseEntity<List<TeamDto>> getAllTeams() {
        return teamService.getAllTeams();
    };
}
