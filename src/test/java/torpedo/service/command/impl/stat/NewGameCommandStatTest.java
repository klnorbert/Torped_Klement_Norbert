package torpedo.service.command.impl.stat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import torpedo.model.GameState;
import torpedo.model.MapVO;
import torpedo.model.PlayerVO;
import torpedo.service.command.performer.AddPerformer;
import torpedo.service.command.performer.DeathShipPerformer;
import torpedo.service.command.performer.HitPerformer;
import torpedo.service.input.UserInputReader;
import torpedo.service.ui.MapPrinter;
import torpedo.service.ui.PrintWrapper;
import torpedo.service.util.CollectionUtil;
import torpedo.service.util.MapUtil;

/**
 * Unit tests for {@link NewGameCommandStat}.
 */
@ExtendWith(MockitoExtension.class)
public class NewGameCommandStatTest {

    private static final String NEW_GAME_COMMAND = "new game";
    private static final String NOT_NEW_GAME_COMMAND = "";

    @Mock
    private PrintWrapper printWrapper;
    private static final MapVO MAP_VO = new MapVO(9, 9, null);
    private PlayerVO playerVO1;
    private GameState gameState;
    private HitPerformer hitPerformer;
    private AddPerformer addPerformer;
    private MapPrinter mapPrinter;
    private CollectionUtil collectionUtil;
    private DeathShipPerformer deathShipPerformer;
    private MapUtil mapUtil;
    private UserInputReader userInputReader;
    private NewGameCommandStat underTest;

    @BeforeEach
    public void setUp() {
        PlayerVO playerVO = new PlayerVO("", MAP_VO, MAP_VO, false);
        playerVO1 = new PlayerVO("", MAP_VO, MAP_VO, false);
        gameState = new GameState(playerVO, playerVO1, false);
        underTest = new NewGameCommandStat(gameState, collectionUtil, hitPerformer, addPerformer,
                deathShipPerformer, mapPrinter, printWrapper, mapUtil,
                userInputReader);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsExit() {
        // given in setup

        // when
        boolean result = underTest.canProcess(NEW_GAME_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsNotExit() {
        // given in setup

        // when
        boolean result = underTest.canProcess(NOT_NEW_GAME_COMMAND);

        // then
        assertFalse(result);
    }

}