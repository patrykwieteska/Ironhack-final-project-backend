package com.predictmatch.edgeservice.services;

import com.predictmatch.edgeservice.proxy.LiveResultsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FixtureService {

    @Autowired
    LiveResultsProxy fixtureProxy;
}
