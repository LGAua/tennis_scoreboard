package service;

import com.lga.tennisscoreboard.dto.MatchDto;
import com.lga.tennisscoreboard.entity.Player;
import com.lga.tennisscoreboard.service.MatchCalculationService;
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

    @ParameterizedTest
    @MethodSource("paramsForAddPointToPlayerOne")
    void addPointToPlayerOne(MatchDto matchDto) {
        calculationService.addPointToPlayerOne(matchDto);

        assertThat(matchDto.getWinner()).isEqualTo(rafael);
        assertThat(matchDto.getSetWinsByPlayerOne()).isEqualTo(2);
        assertThat(matchDto.getScorePlayerOne()).isEqualTo("0");
        assertThat(matchDto.getGameWinsByPlayerOne()).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("paramsForAddPointToPlayerTwo")
    void addPointToPlayerTwo(MatchDto matchDto) {
        calculationService.addPointToPlayerTwo(matchDto);

        assertThat(matchDto.getWinner()).isEqualTo(serena);
        assertThat(matchDto.getSetWinsByPlayerTwo()).isEqualTo(2);
        assertThat(matchDto.getScorePlayerTwo()).isEqualTo("0");
        assertThat(matchDto.getGameWinsByPlayerTwo()).isEqualTo(0);
    }


    @ParameterizedTest
    @MethodSource("paramsForCheckForTieBrake")
    void checkForTieBrake(MatchDto matchDto) {
        calculationService.addPointToPlayerTwo(matchDto);

        assertThat(matchDto.getSetWinsByPlayerTwo()).isEqualTo(1);
        assertThat(matchDto.getSetWinsByPlayerOne()).isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("paramsForCheckForAdvantage")
    void checkForAdvantage(MatchDto matchDto) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = calculationService.getClass().getDeclaredField("isTieBreak");
        declaredField.setAccessible(true);
        declaredField.set(calculationService,false);
//        calculationService.setTieBreak(false);
        calculationService.addPointToPlayerTwo(matchDto);

        assertThat(matchDto.getScorePlayerOne()).isEqualTo("40");
        assertThat(matchDto.getScorePlayerTwo()).isEqualTo(ADVANTAGE);
    }

    @ParameterizedTest
    @MethodSource("paramsForCheckForScoreEqualizing")
    void checkForScoreEqualizing(MatchDto matchDto) {
        calculationService.addPointToPlayerTwo(matchDto);

        assertThat(matchDto.getScorePlayerOne()).isEqualTo("40");
        assertThat(matchDto.getScorePlayerTwo()).isEqualTo("40");
    }

    @ParameterizedTest
    @MethodSource("paramsForCheckForScoreEqualizingDuringTieBreak")
    void checkForScoreEqualizingDuringTieBreak(MatchDto matchDto) {
        calculationService.addPointToPlayerTwo(matchDto);

        assertThat(matchDto.getScorePlayerOne()).isEqualTo("7");
        assertThat(matchDto.getScorePlayerTwo()).isEqualTo("7");
    }

    @ParameterizedTest
    @MethodSource("paramsForCheckForTieBreakScoreLogic")
    void checkForTieBreakScoreLogic(MatchDto matchDto) {
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


    static Stream<Arguments> paramsForAddPointToPlayerOne() {
        return Stream.of(
                Arguments.of(MatchDto.builder()
                        .playerOne(rafael)
                        .playerTwo(serena)
                        .setWinsByPlayerOne(1)
                        .setWinsByPlayerTwo(1)
                        .gameWinsByPlayerOne(5)
                        .gameWinsByPlayerTwo(0)
                        .scorePlayerOne("40")
                        .scorePlayerTwo("0")
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
                        .scorePlayerOne("0")
                        .scorePlayerTwo("40")
                        .build())
        );
    }

    static Stream<Arguments> paramsForCheckForTieBrake() {
        return Stream.of(
                Arguments.of(buildMatchForTieBrakeTest(5, 5))
        );
    }

    static Stream<Arguments> paramsForCheckForAdvantage() {
        return Stream.of(
                Arguments.of(MatchDto.builder()
                        .playerOne(rafael)
                        .playerTwo(serena)
                        .setWinsByPlayerOne(0)
                        .setWinsByPlayerTwo(0)
                        .gameWinsByPlayerOne(0)
                        .gameWinsByPlayerTwo(0)
                        .scorePlayerOne("40")
                        .scorePlayerTwo("40")
                        .build())
        );
    }

    static Stream<Arguments> paramsForCheckForScoreEqualizing() {
        return Stream.of(
                Arguments.of(MatchDto.builder()
                        .playerOne(rafael)
                        .playerTwo(serena)
                        .setWinsByPlayerOne(1)
                        .setWinsByPlayerTwo(1)
                        .gameWinsByPlayerOne(0)
                        .gameWinsByPlayerTwo(5)
                        .scorePlayerOne(ADVANTAGE)
                        .scorePlayerTwo("40")
                        .build())
        );
    }

    static Stream<Arguments> paramsForCheckForScoreEqualizingDuringTieBreak() {
        return Stream.of(
                Arguments.of(MatchDto.builder()
                        .playerOne(rafael)
                        .playerTwo(serena)
                        .setWinsByPlayerOne(0)
                        .setWinsByPlayerTwo(0)
                        .gameWinsByPlayerOne(6)
                        .gameWinsByPlayerTwo(6)
                        .scorePlayerOne(ADVANTAGE)
                        .scorePlayerTwo("7")
                        .build())
        );
    }


    static Stream<Arguments> paramsForCheckForTieBreakScoreLogic() {
        return Stream.of(
                Arguments.of(MatchDto.builder()
                        .playerOne(rafael)
                        .playerTwo(serena)
                        .setWinsByPlayerOne(0)
                        .setWinsByPlayerTwo(0)
                        .gameWinsByPlayerOne(6)
                        .gameWinsByPlayerTwo(6)
                        .scorePlayerOne("0")
                        .scorePlayerTwo("0")
                        .build())
        );
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

    private static MatchDto buildMatchForTieBrakeTest(int gameWinsByPlayerOne, int gameWinsByPlayerTwo) {
        return MatchDto.builder()
                .playerOne(rafael)
                .playerTwo(serena)
                .setWinsByPlayerOne(1)
                .setWinsByPlayerTwo(1)
                .gameWinsByPlayerOne(gameWinsByPlayerOne)
                .gameWinsByPlayerTwo(gameWinsByPlayerTwo)
                .scorePlayerOne("0")
                .scorePlayerTwo("40")
                .build();
    }

}