package torpedo.service.command.impl.ship;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import torpedo.model.GameState;
import torpedo.model.MapVO;
import torpedo.model.PlayerVO;
import torpedo.service.ui.MapPrinter;

/**
 * Unit tests for {@link PrintCommandShip}.
 */
@ExtendWith(MockitoExtension.class)
public class PrintCommandShipTest {

    private static final String PRINT_COMMAND = "print";
    private static final String NOT_PRINT_COMMAND = "not-print";

    private static final MapVO MAP_VO = new MapVO(0, 0, null);
    private PlayerVO playerVO1;
    private GameState gameState;
    @Mock
    private MapPrinter mapPrinter;

    private PrintCommandShip underTest;

    @BeforeEach
    public void setUp() {
        PlayerVO playerVO = new PlayerVO("", MAP_VO, MAP_VO, false);
        playerVO1 = new PlayerVO("", MAP_VO,MAP_VO,true);
        gameState = new GameState(playerVO, playerVO1, true);
        underTest = new PrintCommandShip(gameState, mapPrinter);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenTheGivenCommandIsPrint() {
        // given in setup

        // when
        boolean result = underTest.canProcess(PRINT_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenTheGivenCommandIsNotPrint() {
        // given in setup

        // when
        boolean result = underTest.canProcess(NOT_PRINT_COMMAND);

        // then
        assertFalse(result);
    }

    @Test
    public void testProcessShouldPrintTheCurrentMapFromGameState() {
        // given in setup

        // when
        underTest.process(PRINT_COMMAND);

        // then
        verify(mapPrinter).printMap(MAP_VO);
    }

}
