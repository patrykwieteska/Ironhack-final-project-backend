package com.predictmatch.userinfo.mapper;

import com.predictmatch.userinfo.dao.UserInfo;
import com.predictmatch.userinfo.dto.ExternalTeamDto;
import com.predictmatch.userinfo.dto.TeamDto;
import com.predictmatch.userinfo.dto.UserInfoRequest;
import com.predictmatch.userinfo.dto.UserInfoResponse;

public class Mapper {

    public static UserInfoResponse userInfoEntityToDto(UserInfo userInfo, TeamDto teamDto) {

        return new UserInfoResponse(
                userInfo.getId(),
                userInfo.getUsername(),
                userInfo.getCity(),
                userInfo.getCountry(),
                userInfo.getInfo(),
                userInfo.getEmail(),
                teamDto
        );
    }


    public static UserInfo userInfoRequestToEntity(UserInfoRequest request) {
        return new UserInfo(
                request.getUsername(),
                request.getCity(),
                request.getCountry(),
                request.getInfo(),
                request.getEmail(),
                request.getTeamId()
        );
    }


    public static TeamDto externalTeamToTeamDto(ExternalTeamDto externalTeam) {
        return new TeamDto(externalTeam.getId(),
                externalTeam.getName(),
                externalTeam.getLogo());
    }
}
