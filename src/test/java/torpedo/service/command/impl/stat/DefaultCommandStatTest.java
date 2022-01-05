package torpedo.service.command.impl.stat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import torpedo.service.ui.PrintWrapper;




/**
 * Unit tests for {@link DefaultCommandStat}.
 */
@ExtendWith(MockitoExtension.class)
public class DefaultCommandStatTest {

    private static final String INPUT = "";

    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command";

    @Mock
    private PrintWrapper printWrapper;

    private DefaultCommandStat underTest;

    @BeforeEach
    public void setUp() {
        underTest = new DefaultCommandStat(printWrapper);
    }

    @Test
    public void testCanProcessShouldAlwaysReturnTrue() {
        // given

        // when
        boolean result = underTest.canProcess(INPUT);

        // then
        assertTrue(result);
    }

    @Test
    public void testProcessShouldPrintUnknownCommand() {
        // given

        // when
        underTest.process(INPUT);

        // then
        verify(printWrapper).printLine(UNKNOWN_COMMAND_MESSAGE);
    }
}
