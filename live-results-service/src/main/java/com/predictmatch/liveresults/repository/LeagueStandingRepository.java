package com.predictmatch.liveresults.repository;

import com.predictmatch.liveresults.dao.LeagueStanding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueStandingRepository extends JpaRepository<LeagueStanding,Long> {
}
