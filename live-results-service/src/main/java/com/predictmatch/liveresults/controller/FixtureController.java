package com.predictmatch.liveresults.controller;

import com.predictmatch.liveresults.dto.FixtureDto;

import com.predictmatch.liveresults.service.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.predictmatch.liveresults.enmus.FixtureStatus.*;

@RestController
@RequestMapping("/liveresults/api/v1/fixtures")
public class FixtureController {

    @Autowired
    FixtureService fixtureService;

    @GetMapping("/all")
    public ResponseEntity<List<FixtureDto>> initAllFixtures() {
        return fixtureService.initAllFixtures();
    }

    @GetMapping("/postponed")
    public ResponseEntity<List<FixtureDto>> initPostponedFixtures() {
        return fixtureService.initFixturesByStatus( POSTPONED);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<FixtureDto>> initUpcomingFixtures() {
        return fixtureService.initFixturesByStatus( UPCOMING );
    }

    @GetMapping("/finished")
    public ResponseEntity<List<FixtureDto>> initFinishedFixtures() {
        return fixtureService.initFixturesByStatus( FINISHED);
    }

    @GetMapping("/live")
    public ResponseEntity<List<FixtureDto>> initInProgressFixtures() {
        return fixtureService.initFixturesByStatus( LIVE);
    }


}
