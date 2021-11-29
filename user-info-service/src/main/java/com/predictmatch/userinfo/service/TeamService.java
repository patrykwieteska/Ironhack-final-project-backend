package com.predictmatch.userinfo.service;

import com.predictmatch.userinfo.dto.ExternalTeamDto;
import com.predictmatch.userinfo.dto.TeamDto;
import com.predictmatch.userinfo.mapper.Mapper;
import com.predictmatch.userinfo.proxy.TeamProxy;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    TeamProxy teamProxy;

    @Retry( name="getTeam", fallbackMethod = "getUnknownTeam")
    public TeamDto findTeam(long teamId) {

        Optional<ExternalTeamDto> externalTeamDto = Optional.ofNullable( teamProxy.getTeamById( teamId ).getBody() );

        if (externalTeamDto.isEmpty()){
            return new TeamDto(
                    teamId,
                    "unknown",
                    "unknown",
                    "UNVERIFIED"
            );
    }

        return Mapper.externalTeamToTeamDto(externalTeamDto.get());
    }

    public TeamDto getUnknownTeam(long teamId, Exception e) {

        return new TeamDto(
                teamId,
                "unknown",
                "unknown",
                "UNVERIFIED"
        );
    }
}
