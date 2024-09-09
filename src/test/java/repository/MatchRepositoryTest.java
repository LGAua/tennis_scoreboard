package repository;

import com.lga.tennisscoreboard.entity.Match;
import com.lga.tennisscoreboard.entity.Player;
import com.lga.tennisscoreboard.repository.MatchRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestDataImporter;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MatchRepositoryTest {

    private static final Player roger = Player.builder().id(1L).name("Roger Federer").build();
    private static final Player rafael = Player.builder().id(2L).name("Rafael Nadal").build();
    private static final Player serena = Player.builder().id(3L).name("Serena Williams").build();
    private static final Match newMatch = Match.builder().firstPlayer(serena).secondPlayer(rafael).winner(serena).build();
    private static final Long EXISTING_ID = 1L;
    private static final Long NOT_EXISTING_ID = 5L;

    private static final MatchRepository matchRepository = new MatchRepository();

    @BeforeEach
    void beforeTestExecution() {
        TestDataImporter.importData();
    }

    @AfterEach
    void afterTestExecution() {
        TestDataImporter.cleanData();
    }

    @Test
    void findById() {
        Optional<Match> match1 = matchRepository.findById(1L);
        Optional<Match> match2 = matchRepository.findById(2L);
        Optional<Match> match3 = matchRepository.findById(3L);

        assertThat(match1).isPresent();
        assertThat(match2).isPresent();
        assertThat(match3).isPresent();

        assertThat(match1.get().getWinner().getName()).isEqualTo(rafael.getName());
        assertThat(match2.get().getWinner().getName()).isEqualTo(serena.getName());
        assertThat(match3.get().getWinner().getName()).isEqualTo(rafael.getName());
    }

    @Test
    void findAll() {
        List<Match> matchList = matchRepository.findAll();

        assertThat(matchList).hasSize(3);
    }

    @Test
    void save() {
        matchRepository.save(newMatch);

        Optional<Match> match = matchRepository.findById(4L);
        List<Match> matchList = matchRepository.findAll();

        assertThat(match).isPresent();
        assertThat(match.get().getWinner().getName()).isEqualTo(serena.getName());

        assertThat(matchList).hasSize(4);
    }

    @Test
    void deleteExistingId() {
        matchRepository.delete(EXISTING_ID);

        List<Match> matchList = matchRepository.findAll();

        assertThat(matchList).hasSize(2);
    }

    @Test
    void deleteNotExistingId() {
        matchRepository.delete(NOT_EXISTING_ID);

        List<Match> matchList = matchRepository.findAll();

        assertThat(matchList).hasSize(3);
    }


    @Test
    void updateExistingEntity() {
        newMatch.setId(EXISTING_ID);
        matchRepository.update(newMatch);

        Optional<Match> match = matchRepository.findById(EXISTING_ID);

        assertThat(match).isPresent();
        assertThat(match.get().getWinner().getName()).isEqualTo(serena.getName());

        newMatch.setId(null);
    }

    @Test
    void updateNotExistingEntity() {
        newMatch.setId(NOT_EXISTING_ID);
        matchRepository.update(newMatch);

        Optional<Match> match = matchRepository.findById(NOT_EXISTING_ID);

        assertThat(match).isEmpty();

        newMatch.setId(null);
    }

}