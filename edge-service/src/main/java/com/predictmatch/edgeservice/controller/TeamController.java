package com.predictmatch.edgeservice.controller;

import com.predictmatch.edgeservice.dto.team.TeamDto;
import com.predictmatch.edgeservice.proxy.LiveResultsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = "http://localhost:4200")
public class TeamController
{
    @Autowired
    LiveResultsProxy liveResultsProxy;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<TeamDto>> getAllTeams() {
        return liveResultsProxy.getAllTeams();
    };
}
