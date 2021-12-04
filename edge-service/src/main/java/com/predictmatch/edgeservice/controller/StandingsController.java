package com.predictmatch.edgeservice.controller;

import com.predictmatch.edgeservice.dto.league.LeagueDataDto;
import com.predictmatch.edgeservice.proxy.LiveResultsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/standings")
public class StandingsController {

    @Autowired
    LiveResultsProxy liveResultsProxy;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LeagueDataDto> getLeagueData() {
        return liveResultsProxy.getLeagueData();
    }
}
