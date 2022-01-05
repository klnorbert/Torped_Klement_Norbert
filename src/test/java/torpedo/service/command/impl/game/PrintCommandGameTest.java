package torpedo.service.command.impl.game;

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
import torpedo.service.command.impl.ship.PrintCommandShip;
import torpedo.service.ui.MapPrinter;



/**
 * Unit tests for {@link PrintCommandGame}.
 */
@ExtendWith(MockitoExtension.class)
public class PrintCommandGameTest {

    private static final String PRINT_COMMAND = "print";
    private static final String NOT_PRINT_COMMAND = "not-print";

    private static final MapVO MAP_VO = new MapVO(9, 9, null);
    private PlayerVO playerVO1;
    private GameState gameState;
    @Mock
    private MapPrinter mapPrinter;

    private PrintCommandGame underTest;

    @BeforeEach
    public void setUp() {
        PlayerVO playerVO = new PlayerVO("", MAP_VO, MAP_VO, true);
        playerVO1 = new PlayerVO("", MAP_VO,MAP_VO,false);
        gameState = new GameState(playerVO, playerVO1, true);
        underTest = new PrintCommandGame(gameState, mapPrinter);
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

}
