package com.example.betandwin.match;

import com.example.betandwin.bet.Bet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String host;
    private String visitor;
    private Integer hostScore;
    private Integer visitorScore;

    @OneToMany(mappedBy = "match", cascade = CascadeType.REMOVE)
    private List<Bet> bets;
}
