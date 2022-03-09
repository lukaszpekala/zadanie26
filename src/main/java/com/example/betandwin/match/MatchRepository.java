package com.example.betandwin.match;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findAllByHostScoreNotNullOrderByBetCountDesc();

    List<Match> findAllByHostScoreNullOrderByBetCountDesc();

    @Query("SELECT m FROM Match m JOIN FETCH m.bets b WHERE m.hostScore IS NULL ORDER BY SIZE(b) DESC")
    List<Match> findByHostScoreNullOrderByBetCountDesc(Pageable p);
}
