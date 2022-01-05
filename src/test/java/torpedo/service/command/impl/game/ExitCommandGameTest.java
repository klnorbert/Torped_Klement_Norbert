package torpedo.service.command.impl.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import torpedo.model.GameState;

/**
 * Unit tests for {@link ExitCommandGame}.
 */
public class ExitCommandGameTest {

    private static final String EXIT_COMMAND = "exit";
    private static final String NOT_EXIT_COMMAND = "not-exit";

    private GameState gameState;

    private ExitCommandGame underTest;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(null,null, false);
        underTest = new ExitCommandGame(gameState);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsExit() {
        // given in setup

        // when
        boolean result = underTest.canProcess(EXIT_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsNotExit() {
        // given in setup

        // when
        boolean result = underTest.canProcess(NOT_EXIT_COMMAND);

        // then
        assertFalse(result);
    }

    @Test
    public void testProcessShouldChangeShouldExitFieldOfGameState() {
        // given in setup

        // when
        underTest.process(EXIT_COMMAND);

        // then
        assertTrue(gameState.isShouldExit());
    }

}
