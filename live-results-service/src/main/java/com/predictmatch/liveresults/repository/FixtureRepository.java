package com.predictmatch.liveresults.repository;

import com.predictmatch.liveresults.dao.Fixture;
import com.predictmatch.liveresults.enmus.FixtureStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FixtureRepository extends JpaRepository<Fixture,Long> {

    @Query(value = "SELECT f FROM Fixture f WHERE f.status =:fixtureStatuses ORDER BY f.date ASC")
    List<Fixture> findFixturesByFixtureStatus(FixtureStatus fixtureStatuses);

    @Query(value = "SELECT f FROM Fixture f WHERE f.status='FINISHED' ORDER BY f.date DESC")
    List<Fixture> findFinishedFixtures();

    List<Fixture> findFixtureByRoundOrderByDateAsc(int round);

    @Query(value ="SELECT MIN(f.DATE) FROM FIXTURE f WHERE f.STATUS='UPCOMING'", nativeQuery = true)
    LocalDateTime getNextMatchDate();

    @Query(value ="SELECT COUNT(status) FROM FIXTURE WHERE status='LIVE'", nativeQuery = true)
    int getNumberOfLiveMatches();

    @Query(value = "SELECT f FROM Fixture f WHERE f.homeTeamId =:fixtureId OR f.awayTeamId=:fixtureId ORDER BY f" +
            ".date ASC")
    List<Fixture> findFixtureByTeam(Long fixtureId);
}
