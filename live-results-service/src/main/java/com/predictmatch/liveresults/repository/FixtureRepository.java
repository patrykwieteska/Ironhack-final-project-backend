package com.predictmatch.liveresults.repository;

import com.predictmatch.liveresults.dao.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FixtureRepository extends JpaRepository<Fixture,Long> {

    @Query(value = "SELECT f FROM Fixture f WHERE f.status IN (:fixtureStatuses) ORDER BY f.date ASC")
    public List<Fixture> findFixturesByFixtureStatus(List<String> fixtureStatuses);
}
