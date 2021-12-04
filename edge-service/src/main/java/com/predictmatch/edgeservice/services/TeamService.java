package com.predictmatch.edgeservice.services;

import com.predictmatch.edgeservice.dto.team.TeamDto;
import com.predictmatch.edgeservice.proxy.TeamProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService
{

    @Autowired
    TeamProxy teamProxy;
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        return teamProxy.getAllTeams();
    }
}
