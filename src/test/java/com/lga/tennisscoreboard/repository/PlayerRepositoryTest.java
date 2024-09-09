package com.lga.tennisscoreboard.repository;

import com.lga.tennisscoreboard.entity.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import util.TestDataImporter;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class PlayerRepositoryTest {

    private static final Player roger = Player.builder().id(1L).name("Roger Federer").build();
    private static final Player rafael = Player.builder().id(2L).name("Rafael Nadal").build();
    private static final Player serena = Player.builder().id(3L).name("Serena Williams").build();
    private static final Player newPlayer = Player.builder().name("New Player").build();
    private static final Long NOT_EXISTING_ID = 5L;

    private static PlayerRepository playerRepository = new PlayerRepository();

    @BeforeEach
    void beforeTestExecution() {
        TestDataImporter.importData();
    }

    @AfterEach
    void afterTestExecution() {
        TestDataImporter.cleanData();
    }

    @Test
    void findPlayerByName() {
        Player rogerFederer = playerRepository.findPlayerByName(roger.getName());
        Player rafaelNadal = playerRepository.findPlayerByName(rafael.getName());
        Player serenaWilliams = playerRepository.findPlayerByName(serena.getName());

        assertThat(rogerFederer.getId()).isEqualTo(1);
        assertThat(rafaelNadal.getId()).isEqualTo(2);
        assertThat(serenaWilliams.getId()).isEqualTo(3);
    }

    @Test
    void findById() {
        Optional<Player> player1 = playerRepository.findById(1L);
        Optional<Player> player2 = playerRepository.findById(2L);
        Optional<Player> player3 = playerRepository.findById(3L);

        assertThat(player1).isPresent();
        assertThat(player2).isPresent();
        assertThat(player3).isPresent();

        assertThat(player1.get().getName()).isEqualTo(roger.getName());
        assertThat(player2.get().getName()).isEqualTo(rafael.getName());
        assertThat(player3.get().getName()).isEqualTo(serena.getName());
    }

    @Test
    void findAll() {
        List<Player> playersList = playerRepository.findAll();

        assertThat(playersList).hasSize(3);
        assertThat(playersList).contains(roger, rafael, serena);
    }

    @Test
    void save() {
        playerRepository.save(newPlayer);

        Optional<Player> player = playerRepository.findById(4L);
        List<Player> playerList = playerRepository.findAll();

        assertThat(player).isPresent();
        assertThat(player.get().getName()).isEqualTo(newPlayer.getName());

        assertThat(playerList).hasSize(4);
        assertThat(playerList).contains(roger, rafael, serena, newPlayer);
    }

    @Test
    void deleteExistingId() {
        playerRepository.delete(serena.getId());

        List<Player> playerList = playerRepository.findAll();

        assertThat(playerList).hasSize(2);
        assertThat(playerList).contains(roger, rafael);
    }

    @Test
    void deleteNotExistingId() {
        playerRepository.delete(NOT_EXISTING_ID);

        List<Player> playerList = playerRepository.findAll();

        assertThat(playerList).hasSize(3);
        assertThat(playerList).contains(roger, rafael, serena);
    }


    @Test
    void updateExistingEntity() {
        newPlayer.setId(serena.getId());
        playerRepository.update(newPlayer);

        List<Player> playerList = playerRepository.findAll();

        assertThat(playerList).hasSize(3);
        assertThat(playerList).contains(roger, rafael, newPlayer);

        newPlayer.setId(null);
    }

    @Test
    void updateNotExistingEntity() {
        newPlayer.setId(NOT_EXISTING_ID);
        playerRepository.update(newPlayer);

        List<Player> playerList = playerRepository.findAll();

        assertThat(playerList).hasSize(3);
        assertThat(playerList).contains(roger, rafael, serena);

        newPlayer.setId(null);
    }
}