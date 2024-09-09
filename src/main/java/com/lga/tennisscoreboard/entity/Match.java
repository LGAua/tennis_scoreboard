package com.lga.tennisscoreboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_1", referencedColumnName = "id", nullable = false)
    private Player firstPlayer;

    @ManyToOne
    @JoinColumn(name = "player_2", referencedColumnName = "id", nullable = false)
    private Player secondPlayer;

    @ManyToOne
    @JoinColumn(name = "winner", referencedColumnName = "id", nullable = false)
    private Player winner;
}
