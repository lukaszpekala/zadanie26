package com.example.betandwin.bet;

import com.example.betandwin.match.Match;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer hostScore;
    private Integer visitorScore;
    private String name;
    private Integer bid;
    private Integer win;
    private boolean promo;

    @ManyToOne
    private Match match;
}
