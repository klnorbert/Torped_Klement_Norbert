package torpedo.service.command.impl.game;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import torpedo.service.ui.PrintWrapper;



/**
 * Unit tests for {@link HelpCommandGame}.
 */
@ExtendWith(MockitoExtension.class)
public class HelpCommandGameTest {

    private static final String HELP_COMMAND = "help";
    private static final String NOT_HELP_COMMAND = "";
    private static final String HELP_COMMAND_MESSAGE = "exit: Close the App\n" +
            "print: Print the you map and your hits\n" +
            "hit [A-J] [1-9]: Launce your rocket and make terrorist go Kaboom\n" +
            "end turn: you hand over control to the other player";
    @Mock
    private PrintWrapper printWrapper;

    private HelpCommandGame underTest;

    @BeforeEach
    public void setUp() {
        underTest = new HelpCommandGame(printWrapper);
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