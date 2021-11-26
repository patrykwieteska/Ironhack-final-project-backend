package com.predictmatch.liveresults.repository;

import com.predictmatch.liveresults.model.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<League,Long> {
}
