package com.predictmatch.liveresults.service;

import com.predictmatch.liveresults.dao.Team;
import com.predictmatch.liveresults.dto.TeamDto;
import com.predictmatch.liveresults.mapper.TeamMapper;
import com.predictmatch.liveresults.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    TeamRepository teamRepository;

    @Override
    public ResponseEntity<TeamDto> getTeamById(Long id) {
        Optional<Team> storedTeam = teamRepository.findById( id );

        if(storedTeam.isEmpty())
            throw new EntityNotFoundException("Not found team with id: "+id);

        return ResponseEntity.ok( TeamMapper.entityToDto( storedTeam.get() ) );

    }

    @Override
    public ResponseEntity<List<TeamDto>> getAllTeams() {

        ArrayList<TeamDto> teamDtos = new ArrayList<>();
        List<Team> storedTeams = teamRepository.findAll();

        storedTeams.forEach( team -> teamDtos.add( TeamMapper.entityToDto( team ) ) );

        return ResponseEntity.ok(teamDtos);
    }

}
