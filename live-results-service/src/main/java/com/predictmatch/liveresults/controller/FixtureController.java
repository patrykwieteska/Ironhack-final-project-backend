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
    public ResponseEntity<FixtureDto> getAllFixtures() {
        return fixtureService.initAllMatches();
    }
}
