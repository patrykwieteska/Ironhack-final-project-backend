package com.predictmatch.liveresults.mapper;

import com.predictmatch.liveresults.dto.TeamDto;
import com.predictmatch.liveresults.dao.Team;

public class TeamMapper {

    public static TeamDto entityToDto(Team team) {
        return new TeamDto(
          team.getId(),
          team.getName(),
          team.getForm(),
          team.getLogo()
        );
    }
}
