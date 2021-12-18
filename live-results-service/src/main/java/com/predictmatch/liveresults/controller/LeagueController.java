package com.predictmatch.liveresults.controller;

import com.predictmatch.liveresults.dto.LeagueDto;
import com.predictmatch.liveresults.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/liveresults/api/v1/standings")
public class LeagueController {

    @Autowired
    LeagueService leagueService;

    @GetMapping
    ResponseEntity<LeagueDto> getLeagueData() {
        return leagueService.initLeagueData();
    }

    @GetMapping("/last-round")
    Integer getLastPlayedRound() {
        return leagueService.getLastPlayedRound();
    }
}
