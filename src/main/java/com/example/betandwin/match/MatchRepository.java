package com.example.betandwin.match;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT m FROM Match m JOIN m.bets b WHERE m.hostScore IS NOT NULL GROUP BY m.id ORDER BY SIZE(b) DESC")
    List<Match> findAllByHostScoreNotNullOrderByBetCountDesc();

    @Query("SELECT m FROM Match m JOIN m.bets b WHERE m.hostScore IS NULL GROUP BY m.id ORDER BY SIZE(b) DESC")
    List<Match> findAllByHostScoreNullOrderByBetCountDesc();

    @Query("SELECT m FROM Match m JOIN m.bets b WHERE m.hostScore IS NULL GROUP BY m.id ORDER BY SIZE(b) DESC")
    List<Match> findByHostScoreNullOrderByBetCountDesc(Pageable p);
}
