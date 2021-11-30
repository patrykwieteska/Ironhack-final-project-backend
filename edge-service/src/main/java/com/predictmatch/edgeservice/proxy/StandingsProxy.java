package com.predictmatch.edgeservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("live-results-service")
public interface StandingsProxy {
}
