package torpedo.service.command.impl.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import torpedo.model.GameState;
import torpedo.model.MapVO;
import torpedo.model.PlayerVO;
import torpedo.service.command.performer.DeathShipPerformer;
import torpedo.service.command.performer.HitPerformer;
import torpedo.service.ui.MapPrinter;
import torpedo.service.ui.PrintWrapper;
import torpedo.service.util.CollectionUtil;

/**
 * Unit tests for {@link HitCommandGame}.
 */
@ExtendWith(MockitoExtension.class)
public class HitCommandGameTest {

    private static final String HIT_COMMAND = "hit A 2";
    private static final String NOT_HIT_COMMAND = "";

    @Mock
    private PrintWrapper printWrapper;
    private static final MapVO MAP_VO = new MapVO(9, 9, null);
    private PlayerVO playerVO1;
    private GameState gameState;
    private HitPerformer hitPerformer;
    private MapPrinter mapPrinter;
    private CollectionUtil collectionUtil;
    private DeathShipPerformer deathShipPerformer;
    private HitCommandGame underTest;

    @BeforeEach
    public void setUp() {
        PlayerVO playerVO = new PlayerVO("", MAP_VO, MAP_VO, false);
        playerVO1 = new PlayerVO("", MAP_VO, MAP_VO, false);
        gameState = new GameState(playerVO, playerVO1, false);
        underTest = new HitCommandGame(gameState, collectionUtil, hitPerformer,
                deathShipPerformer, mapPrinter, printWrapper);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsExit() {
        // given in setup

        // when
        boolean result = underTest.canProcess(HIT_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsNotExit() {
        // given in setup

        // when
        boolean result = underTest.canProcess(NOT_HIT_COMMAND);

        // then
        assertFalse(result);
    }

}