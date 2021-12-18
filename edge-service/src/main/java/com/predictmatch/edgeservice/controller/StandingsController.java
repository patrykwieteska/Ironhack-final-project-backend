package com.predictmatch.edgeservice.controller;

import com.predictmatch.edgeservice.dto.league.LastRoundDto;
import com.predictmatch.edgeservice.dto.league.LeagueDataDto;
import com.predictmatch.edgeservice.proxy.LiveResultsProxy;
import com.predictmatch.edgeservice.services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/standings")
@CrossOrigin(origins = "http://localhost:4200")
public class StandingsController {

    @Autowired
    LiveResultsProxy liveResultsProxy;

    @Autowired
    LeagueService leagueService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LeagueDataDto> getLeagueData() {
        return liveResultsProxy.getLeagueData();
    }

    @GetMapping("/last-round")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LastRoundDto> getLastPlayedRound() {
        return leagueService.getLastPlayedRound();
    }
}
