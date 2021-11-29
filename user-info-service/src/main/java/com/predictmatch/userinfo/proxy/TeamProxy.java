package com.predictmatch.userinfo.proxy;

import com.predictmatch.userinfo.dto.ExternalTeamDto;
import com.predictmatch.userinfo.dto.TeamDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("live-results-service")
public interface TeamProxy {

    @GetMapping("/liveresults/api/v1/teams/{id}")
    ResponseEntity<ExternalTeamDto> getTeamById(@PathVariable(name="id") Long id);
}
