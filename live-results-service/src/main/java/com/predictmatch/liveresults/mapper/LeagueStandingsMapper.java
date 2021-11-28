package com.predictmatch.liveresults.mapper;

import com.predictmatch.liveresults.dto.LeagueStandingDto;
import com.predictmatch.liveresults.dao.LeagueStanding;
import com.predictmatch.liveresults.dao.Team;

public class LeagueStandingsMapper {

    public static LeagueStandingDto entityToDto(LeagueStanding leagueStanding, Team team) {

        return new LeagueStandingDto(
                leagueStanding.getRank(),
                TeamMapper.entityToDto( team ),
                leagueStanding.getPoints(),
                leagueStanding.getPlayed(),
                leagueStanding.getWin(),
                leagueStanding.getDraw(),
                leagueStanding.getLose(),
                leagueStanding.getGoalsFor(),
                leagueStanding.getGoalsAgainst(),
                leagueStanding.getGoalsDifference(),
                leagueStanding.getRankStatus()
        );
    }
}
