package com.predictmatch.userinfo.service;

import com.predictmatch.userinfo.dto.ExternalTeamDto;
import com.predictmatch.userinfo.dto.TeamDto;
import com.predictmatch.userinfo.mapper.Mapper;
import com.predictmatch.userinfo.proxy.TeamProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    TeamProxy teamProxy;

    public TeamDto findTeam(long teamId) {

        Optional<ExternalTeamDto> externalTeamDto = Optional.ofNullable(teamProxy.getTeamById( teamId).getBody());

        if(externalTeamDto.isEmpty())
            return null;

        return Mapper.externalTeamToTeamDto(externalTeamDto.get());
    }
}
