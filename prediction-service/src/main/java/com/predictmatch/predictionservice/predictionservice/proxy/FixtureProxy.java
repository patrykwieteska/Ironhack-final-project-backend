package com.predictmatch.predictionservice.predictionservice.proxy;

import com.predictmatch.predictionservice.predictionservice.dto.fixture.FixtureDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("live-results-service")
public interface FixtureProxy {

    @GetMapping("/liveresults/api/v1/fixtures/{id}")
    ResponseEntity<FixtureDto> findFixtureById(@PathVariable(name="id") Long id);
}
