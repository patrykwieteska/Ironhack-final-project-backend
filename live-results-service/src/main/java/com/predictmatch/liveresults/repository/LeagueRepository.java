package com.predictmatch.liveresults.repository;

import com.predictmatch.liveresults.dao.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<League,Long> {

    @Query(nativeQuery = true, value="SELECT MAX(PLAYED) FROM STANDINGS")
    Integer getLastPlayedRound();
}
