package service;

import com.lga.tennisscoreboard.dto.MatchDto;
import com.lga.tennisscoreboard.entity.Match;
import com.lga.tennisscoreboard.entity.Player;
import com.lga.tennisscoreboard.service.MatchCalculationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MatchCalculationServiceTest {

    private static final Player rafael = Player.builder().id(2L).name("Rafael Nadal").build();
    private static final Player serena = Player.builder().id(3L).name("Serena Williams").build();
    private final MatchCalculationService calculationService = new MatchCalculationService();

    @ParameterizedTest
    @MethodSource("paramsForAddPointToPlayerOne")
    void addPointToPlayerOne(MatchDto matchDto) {
        calculationService.addPointToPlayerOne(matchDto);

        assertThat(matchDto.getWinner()).isEqualTo(rafael);
        assertThat(matchDto.getSetWinsByPlayerOne()).isEqualTo(2);
        assertThat(matchDto.getScorePlayerOne()).isEqualTo(0);
        assertThat(matchDto.getGameWinsByPlayerOne()).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("paramsForAddPointToPlayerTwo")
    void addPointToPlayerTwo(MatchDto matchDto) {
        calculationService.addPointToPlayerTwo(matchDto);

        assertThat(matchDto.getWinner()).isEqualTo(serena);
        assertThat(matchDto.getSetWinsByPlayerTwo()).isEqualTo(2);
        assertThat(matchDto.getScorePlayerTwo()).isEqualTo(0);
        assertThat(matchDto.getGameWinsByPlayerTwo()).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("paramsForCheckForTieBrake")
    void checkForTieBrake(MatchDto matchDto) {
        calculationService.addPointToPlayerTwo(matchDto);

        assertThat(matchDto.getSetWinsByPlayerTwo()).isEqualTo(1);
        assertThat(matchDto.getSetWinsByPlayerOne()).isEqualTo(1);
    }


    static Stream<Arguments> paramsForAddPointToPlayerOne() {
        return Stream.of(
                Arguments.of(MatchDto.builder()
                        .playerOne(rafael)
                        .playerTwo(serena)
                        .setWinsByPlayerOne(1)
                        .setWinsByPlayerTwo(1)
                        .gameWinsByPlayerOne(5)
                        .gameWinsByPlayerTwo(0)
                        .scorePlayerOne(40)
                        .scorePlayerTwo(0)
                        .build())
        );
    }

    static Stream<Arguments> paramsForAddPointToPlayerTwo() {
        return Stream.of(
                Arguments.of(MatchDto.builder()
                        .playerOne(rafael)
                        .playerTwo(serena)
                        .setWinsByPlayerOne(1)
                        .setWinsByPlayerTwo(1)
                        .gameWinsByPlayerOne(0)
                        .gameWinsByPlayerTwo(5)
                        .scorePlayerOne(0)
                        .scorePlayerTwo(40)
                        .build())
        );
    }

    static Stream<Arguments> paramsForCheckForTieBrake() {
        return Stream.of(
                Arguments.of(buildMatchForTieBrakeTest(5,5)),
                Arguments.of(buildMatchForTieBrakeTest(6,6)),
                Arguments.of(buildMatchForTieBrakeTest(7,6))
        );
    }

    private static MatchDto buildMatchForTieBrakeTest(int gameWinsByPlayerOne,int gameWinsByPlayerTwo ){
        return MatchDto.builder()
                .playerOne(rafael)
                .playerTwo(serena)
                .setWinsByPlayerOne(1)
                .setWinsByPlayerTwo(1)
                .gameWinsByPlayerOne(gameWinsByPlayerOne)
                .gameWinsByPlayerTwo(gameWinsByPlayerTwo)
                .scorePlayerOne(0)
                .scorePlayerTwo(40)
                .build();
    }

}