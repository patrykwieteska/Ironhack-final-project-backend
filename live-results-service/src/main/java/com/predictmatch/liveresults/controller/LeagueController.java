package com.predictmatch.liveresults.controller;

import com.predictmatch.liveresults.dto.LeagueDto;
import com.predictmatch.liveresults.service.LeagueService;
import com.predictmatch.liveresults.service.LiveResultsService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/liveresults/api/v1/standings")
public class LeagueController {

    @Autowired
    LeagueService leagueService;

    @GetMapping
    ResponseEntity<LeagueDto> getLeagueData() {
        return leagueService.initLeagueData();
    }
}
