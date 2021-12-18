package com.predictmatch.edgeservice.services;

import com.predictmatch.edgeservice.dto.league.LastRoundDto;
import com.predictmatch.edgeservice.proxy.LiveResultsProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LeagueService {

    private final Logger logger = LoggerFactory.getLogger( LeagueService.class );

    @Autowired
    LiveResultsProxy liveResultsProxy;

    public ResponseEntity<LastRoundDto> getLastPlayedRound() {

        logger.info( "Getting last played round" );
        Integer lastRound = liveResultsProxy.getLastPlayedRound();
        int nextRound =1;




        if(lastRound==null || lastRound==0) {
            lastRound=1;
        } else {
            nextRound=lastRound+1;
        }

        LastRoundDto lastRoundDto = new LastRoundDto( lastRound );

        logger.info( "Last played round: {}" ,lastRoundDto);

        return ResponseEntity.ok(lastRoundDto);

    }
}
