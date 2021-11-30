package com.predictmatch.liveresults.service;

import com.predictmatch.liveresults.dto.LeagueDto;
import com.predictmatch.liveresults.dto.LeagueStandingDto;
import com.predictmatch.liveresults.mapper.LeagueStandingsMapper;
import com.predictmatch.liveresults.dao.League;
import com.predictmatch.liveresults.dao.LeagueStanding;
import com.predictmatch.liveresults.dao.Team;
import com.predictmatch.liveresults.repository.LeagueRepository;
import com.predictmatch.liveresults.repository.LeagueStandingRepository;
import com.predictmatch.liveresults.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Value("${api.football.league.id}")
    private String leagueId;

    @Autowired
    LeagueRepository leagueRepository;

    @Autowired
    LeagueStandingRepository leagueStandingRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public ResponseEntity<LeagueDto> initLeagueData() {

        List<LeagueStandingDto> standingsDto = new ArrayList<>();
        LeagueDto leagueDto;

        Optional<League> storedLeague = leagueRepository.findById( Long.parseLong( leagueId));

        if(storedLeague.isEmpty())
            throw new EntityNotFoundException("There is no League with id: "+leagueId);

        leagueDto = new LeagueDto(
                storedLeague.get().getExternalLeagueId(),
                storedLeague.get().getLeagueName(),
                storedLeague.get().getSeason(),
                storedLeague.get().getLogo(),
                storedLeague.get().getCountry(),
                storedLeague.get().getCountryFlag(),
                null
        );

        List<LeagueStanding> standings = leagueStandingRepository.findAll(Sort.by( Sort.Direction.ASC,"rank"));


        standings.forEach( standing -> {

            Optional<Team> team = teamRepository.findById( standing.getTeamId());

            if(team.isEmpty())
                throw new EntityNotFoundException("There is no team with id: " +standing.getTeamId());
            standingsDto.add(LeagueStandingsMapper.entityToDto( standing,team.get()));
        } );


        leagueDto.setStandings( standingsDto );

        return ResponseEntity.ok(leagueDto);
    }
}
