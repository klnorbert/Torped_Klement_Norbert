package torpedo.service.command.impl.ship;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import torpedo.service.command.impl.game.HelpCommandGame;
import torpedo.service.ui.PrintWrapper;


/**
 * Unit tests for {@link HelpCommandShip}.
 */
@ExtendWith(MockitoExtension.class)
public class HelpCommandShipTest {

    private static final String HELP_COMMAND = "help";
    private static final String NOT_HELP_COMMAND = "";
    private static final String HELP_COMMAND_MESSAGE =
            "exit: Close the App\n" +
                    "print: Print the ship place map\n" +
                    "add [A-J] [1-9] [1-4]: Add a ship your fleet size length is locked by the other player\n" +
                    "end turn: you hand over control to the other player\n" +
                    "place [A-J] [1-9] [1-4] [1-5]: you hand over control to the other player\n" +
                    "end place: you end the ship place phase when you didn't place a ship this round\n";
    @Mock
    private PrintWrapper printWrapper;

    private HelpCommandShip underTest;

    @BeforeEach
    public void setUp() {
        underTest = new HelpCommandShip(printWrapper);
    }

    @Test
    public void testCanProcessShouldAlwaysReturnTrue() {
        // given

        // when
        boolean result = underTest.canProcess(HELP_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsNotExit() {
        // given in setup

        // when
        boolean result = underTest.canProcess(NOT_HELP_COMMAND);

        // then
        assertFalse(result);
    }

    @Test
    public void testProcessShouldPrintUnknownCommand() {
        // given

        // when
        underTest.process(HELP_COMMAND);

        // then
        verify(printWrapper).printLine(HELP_COMMAND_MESSAGE);
    }

}