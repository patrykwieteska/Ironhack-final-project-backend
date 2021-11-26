package com.predictmatch.liveresults.repository;

import com.predictmatch.liveresults.model.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixtureRepository extends JpaRepository<Fixture,Long> {
}
