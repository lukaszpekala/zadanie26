package com.example.betandwin.bet;

import com.example.betandwin.match.Match;
import com.example.betandwin.match.MatchAndBetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BetController {
    private final MatchAndBetService matchAndBetService;

    public BetController(MatchAndBetService matchAndBetService) {
        this.matchAndBetService = matchAndBetService;
    }

    @GetMapping("/bet")
    public String bet(@RequestParam Long id, @RequestParam boolean promo, Model model) {
        Bet bet = new Bet();
        Match match = matchAndBetService.findById(id);
        bet.setMatch(match);
        bet.setPromo(promo);
        model.addAttribute("bet", bet);
        model.addAttribute("match", match);
        return "/bet";
    }

    @PostMapping("/bet")
    public String bet(Bet bet) {
        matchAndBetService.placeBet(bet);
        return "redirect:/";
    }
}
