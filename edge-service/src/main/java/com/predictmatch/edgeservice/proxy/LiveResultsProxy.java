package com.predictmatch.edgeservice.proxy;

import com.predictmatch.edgeservice.dto.fixture.FixtureDto;
import com.predictmatch.edgeservice.dto.fixture.FixtureResponseDto;
import com.predictmatch.edgeservice.dto.league.LeagueDataDto;
import com.predictmatch.edgeservice.dto.team.TeamDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("live-results-service")
public interface LiveResultsProxy {

    @GetMapping("/liveresults/api/v1/fixtures")
    ResponseEntity<FixtureResponseDto> initAllFixtures(@RequestParam(required = false, name="round") Integer round);

    @GetMapping("/liveresults/api/v1/fixtures/status/{fixtureStatus}")
    ResponseEntity<FixtureResponseDto> initPostponedFixtures(@PathVariable(name="fixtureStatus") String fixtureStatus);

    @GetMapping("/liveresults/api/v1/fixtures/team/{id}")
    ResponseEntity<FixtureResponseDto> initFixturesByTeamId(@PathVariable(name="id") Long id);

    @GetMapping("/liveresults/api/v1/fixtures/{id}")
    ResponseEntity<FixtureDto> findFixtureById(@PathVariable(name="id") Long id);

    @GetMapping("/liveresults/api/v1/teams")
    ResponseEntity<List<TeamDto>> getAllTeams();

    @GetMapping("/liveresults/api/v1/standings")
    ResponseEntity<LeagueDataDto> getLeagueData();


}
