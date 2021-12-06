package com.predictmatch.userinfo.mapper;

import com.predictmatch.userinfo.dao.UserInfo;
import com.predictmatch.userinfo.dao.auth.User;
import com.predictmatch.userinfo.dto.ExternalTeamDto;
import com.predictmatch.userinfo.dto.TeamDto;
import com.predictmatch.userinfo.dto.UserInfoResponse;
import com.predictmatch.userinfo.dto.auth.RegisterRequest;

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


    public static UserInfo registerRequestToUserInfoEntity(RegisterRequest request, User user) {
        return new UserInfo(user.getId(),
                request.getUsername(),
                request.getCity(),
                request.getCountry(),
                request.getInfo(),
                request.getEmail(),
                request.getTeamId(),user
        );
    }


    public static TeamDto externalTeamToTeamDto(ExternalTeamDto externalTeam) {
        return new TeamDto(externalTeam.getId(),
                externalTeam.getName(),
                externalTeam.getLogo());
    }
}
