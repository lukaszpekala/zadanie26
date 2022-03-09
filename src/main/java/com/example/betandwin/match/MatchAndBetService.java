package com.example.betandwin.match;

import com.example.betandwin.bet.Bet;
import com.example.betandwin.bet.BetRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class MatchAndBetService {
    private static final int WRONG_GUESS = 0;
    private static final int RATE_BONUS = 2;
    private static final int PROMO_BONUS = 2;

    private final MatchRepository matchRepository;
    private final BetRepository betRepository;

    public MatchAndBetService(MatchRepository matchRepository, BetRepository betRepository) {
        this.matchRepository = matchRepository;
        this.betRepository = betRepository;
    }

    public List<Match> findAllByHostScoreNullOrderByBetCountDesc() {
        return matchRepository.findAllByHostScoreNullOrderByBetCountDesc();
    }

    public void save(Match match) {
        matchRepository.save(match);
    }

    public List<Match> findAllByHostScoreNotNullOrderByBetCountDesc() {
        return matchRepository.findAllByHostScoreNotNullOrderByBetCountDesc();
    }

    public List<Match> findTop3ByHostScoreNullOrderByBetCountDesc() {
        return matchRepository.findByHostScoreNullOrderByBetCountDesc(PageRequest.of(0, 3));
    }

    public void deleteById(Long id) {
        Match match = matchRepository.findById(id).orElse(null);
        matchRepository.delete(match);
    }

    public Match findById(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    public void addResult(Match match) {
        calculateWins(match);
        matchRepository.save(match);
    }

    private void calculateWins(Match match) {
        List<Bet> bets = match.getBets();
        if (bets != null) {
            for (Bet bet : bets) {
                if (Objects.equals(match.getHostScore(), bet.getHostScore()) && Objects.equals(match.getVisitorScore(), bet.getVisitorScore())) {
                    int bonus = RATE_BONUS;
                    if (bet.isPromo()) {
                        bonus += PROMO_BONUS;
                    }
                    bet.setWin(bet.getBid() * (match.getHostScore() + match.getVisitorScore() + bonus));
                } else {
                    bet.setWin(WRONG_GUESS);
                }
                betRepository.save(bet);
            }
        }
    }

    public void placeBet(Bet bet) {
        betRepository.save(bet);
    }
}
