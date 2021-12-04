package com.predictmatch.edgeservice.services;

import com.predictmatch.edgeservice.dto.team.TeamDto;
import com.predictmatch.edgeservice.proxy.LiveResultsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService
{

    @Autowired
    LiveResultsProxy liveResultsProxy;
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        return liveResultsProxy.getAllTeams();
    }
}
