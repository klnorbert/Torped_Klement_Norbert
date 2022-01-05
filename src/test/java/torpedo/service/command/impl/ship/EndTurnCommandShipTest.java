package torpedo.service.command.impl.ship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import torpedo.model.GameState;
import torpedo.model.MapVO;
import torpedo.model.PlayerVO;
import torpedo.service.command.impl.game.EndTurnCommandGame;
import torpedo.service.ui.PrintWrapper;


/**
 * Unit tests for {@link EndTurnCommandShip}.
 */
@ExtendWith(MockitoExtension.class)
public class EndTurnCommandShipTest {

    private static final String END_TURN_COMMAND = "end turn";
    private static final String NOT_END_TURN_COMMAND = "";

    @Mock
    private PrintWrapper printWrapper;
    private static final MapVO MAP_VO = new MapVO(9, 9, null);
    private PlayerVO playerVO1;
    private GameState gameState;
    private EndTurnCommandShip underTest;

    @BeforeEach
    public void setUp() {
        PlayerVO playerVO = new PlayerVO("", MAP_VO, MAP_VO, false);
        playerVO1 = new PlayerVO("", MAP_VO,MAP_VO,false);
        gameState = new GameState(playerVO, playerVO1, false);
        underTest = new EndTurnCommandShip(gameState, printWrapper);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsExit() {
        // given in setup

        // when
        boolean result = underTest.canProcess(END_TURN_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsNotExit() {
        // given in setup

        // when
        boolean result = underTest.canProcess(NOT_END_TURN_COMMAND);

        // then
        assertFalse(result);
    }

}