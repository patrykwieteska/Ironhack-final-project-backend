package com.predictmatch.liveresults.mapper;

import com.predictmatch.liveresults.dto.FixtureDto;
import com.predictmatch.liveresults.dao.Fixture;
import com.predictmatch.liveresults.dao.Team;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public class FixtureMapper {

    public static FixtureDto entityToDto(Fixture fixture, Team homeTeam, Team awayTeam) {

        return new FixtureDto(
                fixture.getExternalFixtureId(),
                fixture.getLeagueId(),
                fixture.getRound(),
                fixture.getDate(),
                fixture.getTimezone(),
                fixture.getReferee(),
                fixture.getVenueName(),
                fixture.getVenueCity(),
                fixture.getStatus(),
                fixture.getElapsedTime(),
                TeamMapper.entityToDto( homeTeam ),
                TeamMapper.entityToDto( awayTeam),
                fixture.getHomeIsWinner(),
                fixture.getAwayIsWinner(),
                fixture.getHomeGoals(),
                fixture.getAwayGoals()

        );
    }
}
