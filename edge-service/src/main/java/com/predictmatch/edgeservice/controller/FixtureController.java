package com.predictmatch.edgeservice.controller;

import com.predictmatch.edgeservice.dto.fixture.FixtureDto;
import com.predictmatch.edgeservice.dto.fixture.FixtureResponseDto;
import com.predictmatch.edgeservice.proxy.LiveResultsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fixtures")
public class FixtureController
{

    @Autowired
    LiveResultsProxy liveResultsProxy;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<FixtureResponseDto> initAllFixtures(@RequestParam(required = false, name="round") Integer round) {
        return liveResultsProxy.initAllFixtures(round);
    }

    @GetMapping("/status/{fixtureStatus}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<FixtureResponseDto> initFixturesByStatus(@PathVariable(name="fixtureStatus") String fixtureStatus) {
        return liveResultsProxy.initFixturesByStatus(fixtureStatus);
    }

    @GetMapping("/team/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<FixtureResponseDto> initFixturesByTeamId(@PathVariable(name="id") Long id) {
        return liveResultsProxy.initFixturesByTeamId(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<FixtureDto> findFixtureById(@PathVariable(name="id") Long id) {
        return liveResultsProxy.findFixtureById(id);
    }


}
