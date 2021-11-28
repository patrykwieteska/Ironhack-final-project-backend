package com.predictmatch.liveresults.repository;

import com.predictmatch.liveresults.dao.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
}
