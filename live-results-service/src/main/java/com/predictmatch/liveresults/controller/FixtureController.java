package com.predictmatch.liveresults.controller;

import com.predictmatch.liveresults.dto.FixtureDto;

import com.predictmatch.liveresults.dto.FixtureResponseDto;
import com.predictmatch.liveresults.enmus.FixtureStatus;
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

    @GetMapping
    public ResponseEntity<FixtureResponseDto> initAllFixtures(@RequestParam(required = false, name="round") Integer round) {
        return fixtureService.initAllFixtures(round);
    }

    @GetMapping("/{fixtureStatus}")
    public ResponseEntity<FixtureResponseDto> initPostponedFixtures(@PathVariable(name="fixtureStatus") FixtureStatus fixtureStatus) {
        return fixtureService.initFixturesByStatus( fixtureStatus);
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<FixtureResponseDto> initFixturesByTeamId(@PathVariable(name="id") Long id) {
        return fixtureService.initFixturesByTeamId(id);
    }

}
