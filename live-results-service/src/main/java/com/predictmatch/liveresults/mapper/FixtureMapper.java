package com.predictmatch.liveresults.mapper;

import com.predictmatch.liveresults.dto.FixtureDto;
import com.predictmatch.liveresults.dao.Fixture;
import com.predictmatch.liveresults.dao.Team;

public class FixtureMapper {

    public static FixtureDto entityToDto(Fixture fixture, Team homeTeam, Team awayTeam) {

        return new FixtureDto(
                fixture.getExternalFixtureId(),
                fixture.getDate(),
                fixture.getTimezone(),
                fixture.getReferee(),
                fixture.getVenueName(),
                fixture.getVenueCity(),
                fixture.getStatus(),
                TeamMapper.entityToDto( homeTeam ),
                TeamMapper.entityToDto( awayTeam),
                fixture.getHomeIsWinner(),
                fixture.getAwayIsWinner(),
                fixture.getHomeHalfTimeGoals(),
                fixture.getAwayHalfTimeGoals(),
                fixture.getHomeFullTimeGoals(),
                fixture.getAwayFullTimeGoals(),
                fixture.getLeagueId()
        );
    }
}
