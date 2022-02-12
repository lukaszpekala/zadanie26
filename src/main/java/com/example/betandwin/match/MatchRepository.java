package com.example.betandwin.match;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findAllByHostScoreNotNullOrderByBetCountDesc();

    List<Match> findAllByHostScoreNullOrderByBetCountDesc();

    List<Match> findTop3ByHostScoreNullOrderByBetCountDesc();
}
