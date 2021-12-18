package com.predictmatch.liveresults.service;

import com.predictmatch.liveresults.dao.Fixture;
import com.predictmatch.liveresults.dao.Team;
import com.predictmatch.liveresults.dto.FixtureDto;
import com.predictmatch.liveresults.dto.FixtureResponseDto;
import com.predictmatch.liveresults.enmus.FixtureStatus;
import com.predictmatch.liveresults.mapper.FixtureMapper;
import com.predictmatch.liveresults.repository.FixtureRepository;
import com.predictmatch.liveresults.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FixtureServiceImpl implements FixtureService {

    @Autowired
    FixtureRepository fixtureRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public ResponseEntity<FixtureResponseDto> initAllFixtures(Integer round) {
        List<FixtureDto> fixturesDtos = new ArrayList<>();
        List<Fixture> storedFixtures;

        if(round == null) {
            storedFixtures = fixtureRepository.findAll();
        } else {
            storedFixtures = fixtureRepository.findFixtureByRoundOrderByDateAsc( round );

            if(storedFixtures.size() == 0)
                return ResponseEntity.ok(new FixtureResponseDto(
                        "Not found round: "+round+". There are rounds only between 1 and 34", 0,
                        fixturesDtos));
        }


        fixturesDtos= convertFixtureListToFixtureDtoList(storedFixtures);

        return ResponseEntity.ok(new FixtureResponseDto("OK",fixturesDtos.size(),fixturesDtos));
    }

    @Override
    public ResponseEntity<FixtureResponseDto> initFixturesByStatus(FixtureStatus fixtureStatus) {
        List<FixtureDto> fixturesDtos = new ArrayList<>();

        List<Fixture> storedFixtures =
                fixtureRepository.findFixturesByFixtureStatus( fixtureStatus );

        if(storedFixtures.size() == 0)
            return ResponseEntity.ok(new FixtureResponseDto("No fixtures with status: "+fixtureStatus, 0,fixturesDtos));

        fixturesDtos= convertFixtureListToFixtureDtoList(storedFixtures);

        return ResponseEntity.ok(new FixtureResponseDto("OK",fixturesDtos.size(),fixturesDtos));
    }

    @Override
    public ResponseEntity<FixtureResponseDto> initFixturesByTeamId(Long id) {
        List<FixtureDto> fixturesDtos = new ArrayList<>();

        List<Fixture> storedFixtures = fixtureRepository.findFixtureByTeam(id);

        if(storedFixtures.size()==0)
            return ResponseEntity.ok(new FixtureResponseDto(
                    "Not found fixtures for teamId: "+id, 0,
                    fixturesDtos));

        fixturesDtos= convertFixtureListToFixtureDtoList(storedFixtures);

        return ResponseEntity.ok(new FixtureResponseDto("OK",fixturesDtos.size(),fixturesDtos));
    }

    @Override
    public ResponseEntity<FixtureDto> findFixtureById(Long id) {
        Optional<Fixture> storedFixture = fixtureRepository.findById( id );

        if (storedFixture.isEmpty())
            throw new EntityNotFoundException( "Not found fixture with id: " + id );

        List<FixtureDto> fixturesDtos = convertFixtureListToFixtureDtoList( List.of( storedFixture.get() ) );


        return ResponseEntity.ok(fixturesDtos.get( 0 ));
    }

    private List<FixtureDto> convertFixtureListToFixtureDtoList(List<Fixture> storedFixtures) {
        List<FixtureDto> fixtureDtos = new ArrayList<>();

        storedFixtures.forEach( fixture -> {
            Optional<Team> storedHomeTeam = teamRepository.findById( fixture.getHomeTeamId());
            Optional<Team> storedAwayTeam = teamRepository.findById( fixture.getAwayTeamId());

            if(storedHomeTeam.isEmpty()) {
                throw new EntityNotFoundException("Not found home team with id: "+fixture.getHomeTeamId());
            }

            if(storedAwayTeam.isEmpty()) {
                throw new EntityNotFoundException("Not found home team with id: "+fixture.getAwayTeamId());
            }

            fixtureDtos.add( FixtureMapper.entityToDto(fixture,storedHomeTeam.get(),storedAwayTeam.get()));

        });

        return fixtureDtos;
    }
}
