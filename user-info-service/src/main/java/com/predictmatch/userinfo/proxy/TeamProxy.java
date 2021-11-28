package com.predictmatch.userinfo.proxy;

import com.predictmatch.userinfo.dto.TeamDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("LIVE-RESULTS-SERVICE")
public interface TeamProxy {


    @GetMapping("/liveresults/api/v1/teams{id}")
    ResponseEntity<TeamDto> getTeamById(@PathVariable(name="id") Long id);

}
