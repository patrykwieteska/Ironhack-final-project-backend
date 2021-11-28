package com.predictmatch.liveresults.controller;

import com.predictmatch.liveresults.dto.FixtureDto;

import com.predictmatch.liveresults.dto.FixtureResponseDto;
import com.predictmatch.liveresults.service.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.predictmatch.liveresults.enmus.FixtureStatus.*;

@RestController
@RequestMapping("/liveresults/api/v1/fixtures")
public class FixtureController {

    @Autowired
    FixtureService fixtureService;

    @GetMapping("/all")
    public ResponseEntity<FixtureResponseDto> initAllFixtures() {
        return fixtureService.initAllFixtures();
    }

    @GetMapping("/postponed")
    public ResponseEntity<FixtureResponseDto> initPostponedFixtures() {
        return fixtureService.initFixturesByStatus( POSTPONED);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<FixtureResponseDto> initUpcomingFixtures() {
        return fixtureService.initFixturesByStatus( UPCOMING );
    }

    @GetMapping("/finished")
    public ResponseEntity<FixtureResponseDto> initFinishedFixtures() {
        return fixtureService.initFixturesByStatus( FINISHED);
    }

    @GetMapping("/live")
    public ResponseEntity<FixtureResponseDto> initInProgressFixtures() {
        return fixtureService.initFixturesByStatus( LIVE);
    }

    @GetMapping
    public ResponseEntity<FixtureResponseDto> initFixturesByRound(@RequestParam(name="round") int round) {
        return fixtureService.initFixturesByRound(round);
    }


}
