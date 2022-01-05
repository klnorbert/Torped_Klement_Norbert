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
import torpedo.service.command.performer.AddPerformer;
import torpedo.service.command.performer.DeathShipPerformer;
import torpedo.service.ui.MapPrinter;
import torpedo.service.ui.PrintWrapper;
import torpedo.service.util.CollectionUtil;


/**
 * Unit tests for {@link PlaceCommandShip}.
 */
@ExtendWith(MockitoExtension.class)
public class PlaceCommandShipTest {

    private static final String PLACE_COMMAND = "place C 4 2 2";
    private static final String NOT_PLACE_COMMAND = "";

    @Mock
    private PrintWrapper printWrapper;
    private static final MapVO MAP_VO = new MapVO(9, 9, null);
    private PlayerVO playerVO1;
    private GameState gameState;
    private AddPerformer addPerformer;
    private MapPrinter mapPrinter;
    private CollectionUtil collectionUtil;
    private DeathShipPerformer deathShipPerformer;
    private PlaceCommandShip underTest;

    @BeforeEach
    public void setUp() {
        PlayerVO playerVO = new PlayerVO("", MAP_VO, MAP_VO, true);
        playerVO1 = new PlayerVO("", MAP_VO, MAP_VO, true);
        gameState = new GameState(playerVO, playerVO1, true);
        gameState.setShipFootage(2);
        gameState.setShipPlace(false);
        underTest = new PlaceCommandShip(gameState, collectionUtil, addPerformer,
                mapPrinter, printWrapper);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsExit() {
        // given in setup

        // when
        boolean result = underTest.canProcess(PLACE_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsNotExit() {
        // given in setup

        // when
        boolean result = underTest.canProcess(NOT_PLACE_COMMAND);

        // then
        assertFalse(result);
    }

}