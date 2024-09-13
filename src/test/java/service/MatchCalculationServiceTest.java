package service;

import com.lga.tennisscoreboard.dto.MatchDto;
import com.lga.tennisscoreboard.entity.Player;
import com.lga.tennisscoreboard.service.MatchCalculationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MatchCalculationServiceTest {

    private static final Player rafael = Player.builder().id(2L).name("Rafael Nadal").build();
    private static final Player serena = Player.builder().id(3L).name("Serena Williams").build();
    private static final String ADVANTAGE = "AD";

    private final MatchCalculationService calculationService = new MatchCalculationService();

    @Test
    void addPointToPlayerOne() {
        MatchDto matchDto = MatchDto.builder()
                .playerOne(rafael)
                .playerTwo(serena)
                .setWinsByPlayerOne(1)
                .setWinsByPlayerTwo(1)
                .gameWinsByPlayerOne(5)
                .gameWinsByPlayerTwo(0)
                .scorePlayerOne("40")
                .scorePlayerTwo("0")
                .build();

        calculationService.addPointToPlayerOne(matchDto);

        assertThat(matchDto.getWinner()).isEqualTo(rafael);
        assertThat(matchDto.getSetWinsByPlayerOne()).isEqualTo(2);
        assertThat(matchDto.getScorePlayerOne()).isEqualTo("0");
        assertThat(matchDto.getGameWinsByPlayerOne()).isEqualTo(0);
    }

    @Test
    void addPointToPlayerTwo() {
        MatchDto matchDto = MatchDto.builder()
                .playerOne(rafael)
                .playerTwo(serena)
                .setWinsByPlayerOne(1)
                .setWinsByPlayerTwo(1)
                .gameWinsByPlayerOne(0)
                .gameWinsByPlayerTwo(5)
                .scorePlayerOne("0")
                .scorePlayerTwo("40")
                .build();

        calculationService.addPointToPlayerTwo(matchDto);

        assertThat(matchDto.getWinner()).isEqualTo(serena);
        assertThat(matchDto.getSetWinsByPlayerTwo()).isEqualTo(2);
        assertThat(matchDto.getScorePlayerTwo()).isEqualTo("0");
        assertThat(matchDto.getGameWinsByPlayerTwo()).isEqualTo(0);
    }


    @Test
    void checkForTieBrake() {
        MatchDto matchDto = MatchDto.builder()
                .playerOne(rafael)
                .playerTwo(serena)
                .setWinsByPlayerOne(1)
                .setWinsByPlayerTwo(1)
                .gameWinsByPlayerOne(6)
                .gameWinsByPlayerTwo(5)
                .scorePlayerOne("0")
                .scorePlayerTwo("40")
                .build();

        calculationService.addPointToPlayerTwo(matchDto);


        assertThat(matchDto.getSetWinsByPlayerTwo()).isEqualTo(1);
        assertThat(matchDto.getSetWinsByPlayerOne()).isEqualTo(1);

    }

    @Test
    void checkForAdvantage() throws NoSuchFieldException, IllegalAccessException {
        MatchDto matchDto = MatchDto.builder()
                .playerOne(rafael)
                .playerTwo(serena)
                .setWinsByPlayerOne(0)
                .setWinsByPlayerTwo(0)
                .gameWinsByPlayerOne(0)
                .gameWinsByPlayerTwo(0)
                .scorePlayerOne("40")
                .scorePlayerTwo("40")
                .build();

        Field declaredField = calculationService.getClass().getDeclaredField("isTieBreak");
        declaredField.setAccessible(true);
        declaredField.set(calculationService,false);
        calculationService.addPointToPlayerTwo(matchDto);

        assertThat(matchDto.getScorePlayerOne()).isEqualTo("40");
        assertThat(matchDto.getScorePlayerTwo()).isEqualTo(ADVANTAGE);
    }

    @Test
    void checkForScoreEqualizing() {
        MatchDto matchDto = MatchDto.builder()
                .playerOne(rafael)
                .playerTwo(serena)
                .setWinsByPlayerOne(1)
                .setWinsByPlayerTwo(1)
                .gameWinsByPlayerOne(0)
                .gameWinsByPlayerTwo(5)
                .scorePlayerOne(ADVANTAGE)
                .scorePlayerTwo("40")
                .build();
        calculationService.addPointToPlayerTwo(matchDto);

        assertThat(matchDto.getScorePlayerOne()).isEqualTo("40");
        assertThat(matchDto.getScorePlayerTwo()).isEqualTo("40");
    }

    @Test
    void checkForScoreEqualizingDuringTieBreak() {
        MatchDto matchDto = MatchDto.builder()
                .playerOne(rafael)
                .playerTwo(serena)
                .setWinsByPlayerOne(0)
                .setWinsByPlayerTwo(0)
                .gameWinsByPlayerOne(6)
                .gameWinsByPlayerTwo(6)
                .scorePlayerOne(ADVANTAGE)
                .scorePlayerTwo("7")
                .build();

        calculationService.addPointToPlayerTwo(matchDto);

        assertThat(matchDto.getScorePlayerOne()).isEqualTo("7");
        assertThat(matchDto.getScorePlayerTwo()).isEqualTo("7");
    }

    @Test
    void checkForTieBreakScoreLogic() {
        MatchDto matchDto = MatchDto.builder()
                .playerOne(rafael)
                .playerTwo(serena)
                .setWinsByPlayerOne(0)
                .setWinsByPlayerTwo(0)
                .gameWinsByPlayerOne(6)
                .gameWinsByPlayerTwo(6)
                .scorePlayerOne("0")
                .scorePlayerTwo("0")
                .build();

        calculationService.addPointToPlayerTwo(matchDto);

        assertThat(matchDto.getScorePlayerOne()).isEqualTo("0");
        assertThat(matchDto.getScorePlayerTwo()).isEqualTo("1");
    }

    @ParameterizedTest
    @MethodSource("paramsForCheckForTieBreakGameFinish")
    void checkForTieBreakGameFinish(MatchDto matchDto) {
        calculationService.addPointToPlayerTwo(matchDto);

        assertThat(matchDto.getSetWinsByPlayerOne()).isEqualTo(0);
        assertThat(matchDto.getSetWinsByPlayerTwo()).isEqualTo(1);
    }

    static Stream<Arguments> paramsForCheckForTieBreakGameFinish() {
        return Stream.of(
                Arguments.of(MatchDto.builder()
                        .playerOne(rafael)
                        .playerTwo(serena)
                        .setWinsByPlayerOne(0)
                        .setWinsByPlayerTwo(0)
                        .gameWinsByPlayerOne(6)
                        .gameWinsByPlayerTwo(6)
                        .scorePlayerOne("6")
                        .scorePlayerTwo("7")
                        .build()),
                Arguments.of(MatchDto.builder()
                        .playerOne(rafael)
                        .playerTwo(serena)
                        .setWinsByPlayerOne(0)
                        .setWinsByPlayerTwo(0)
                        .gameWinsByPlayerOne(6)
                        .gameWinsByPlayerTwo(6)
                        .scorePlayerOne("7")
                        .scorePlayerTwo("AD")
                        .build()),
                Arguments.of(MatchDto.builder()
                        .playerOne(rafael)
                        .playerTwo(serena)
                        .setWinsByPlayerOne(0)
                        .setWinsByPlayerTwo(0)
                        .gameWinsByPlayerOne(6)
                        .gameWinsByPlayerTwo(6)
                        .scorePlayerOne("5")
                        .scorePlayerTwo("6")
                        .build())
        );
    }
}