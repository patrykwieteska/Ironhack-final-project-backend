package com.predictmatch.liveresults.controller;

import com.predictmatch.liveresults.dto.FixtureDto;
import com.predictmatch.liveresults.service.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/predictmatch/api/v1/fixtures")
public class FixtureController {

    @Autowired
    FixtureService fixtureService;

    @GetMapping("/all")
    public ResponseEntity<FixtureDto> initAllFixtures() {
        return fixtureService.initAllFixtures();
    }

    @GetMapping("/postponed")
    public ResponseEntity<FixtureDto> initPostponedFixtures() {
        return fixtureService.initPostponedFixtures();
    }

    @GetMapping("/upcoming")
    public ResponseEntity<FixtureDto> initUpcomingFixtures() {
        return fixtureService.initUpcomingFixtures();
    }

    @GetMapping("/finished")
    public ResponseEntity<FixtureDto> initFinishedFixtures() {
        return fixtureService.initFinishedFixtures();
    }

    @GetMapping("/in-progress")
    public ResponseEntity<FixtureDto> initInProgressFixtures() {
        return fixtureService.initInProgressFixtures();
    }


}
