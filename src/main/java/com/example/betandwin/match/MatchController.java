package com.example.betandwin.match;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class MatchController {
    private final MatchAndBetService matchAndBetService;

    public MatchController(MatchAndBetService matchAndBetService) {
        this.matchAndBetService = matchAndBetService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Match> matchList = matchAndBetService.findAllByHostScoreNullOrderByBetCountDesc();
        List<Match> topMatches = matchAndBetService.findTop3ByHostScoreNullOrderByBetCountDesc();
        model.addAttribute("match", new Match());
        model.addAttribute("matchList", matchList);
        model.addAttribute("topList", topMatches);
        return "home";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("match", new Match());
        return "/add";
    }

    @PostMapping("/add")
    public String add(Match match) {
        matchAndBetService.save(match);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Match> matchList = matchAndBetService.findAllByHostScoreNullOrderByBetCountDesc();
        List<Match> doneMatchList = matchAndBetService.findAllByHostScoreNotNullOrderByBetCountDesc();
        model.addAttribute("match", new Match());
        model.addAttribute("matchList", matchList);
        model.addAttribute("doneMatchList", doneMatchList);
        return "/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        matchAndBetService.deleteById(id);
        return "redirect:/list";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        Match match = matchAndBetService.findById(id);
        model.addAttribute("match", match);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(Match match) {
        matchAndBetService.addResult(match);
        return "redirect:/list";
    }

    @GetMapping("/show")
    public String show(@RequestParam Long id, Model model) {
        Match match = matchAndBetService.findById(id);
        model.addAttribute("match", match);
        return "show";
    }

    @GetMapping("/results")
    public String results(Model model) {
        List<Match> matchList = matchAndBetService.findAllByHostScoreNotNullOrderByBetCountDesc();
        model.addAttribute("match", new Match());
        model.addAttribute("matchList", matchList);
        return "results";
    }

    @GetMapping("/rules")
    public String rules() {
        return "rules";
    }
}
