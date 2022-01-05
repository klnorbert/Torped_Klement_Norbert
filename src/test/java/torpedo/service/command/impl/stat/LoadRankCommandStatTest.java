package torpedo.service.command.impl.stat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import torpedo.service.ui.PrintWrapper;


/**
 * Unit tests for {@link LoadRankCommandStat}.
 */
@ExtendWith(MockitoExtension.class)
public class LoadRankCommandStatTest {

    private static final String LOAD_RANK_COMMAND = "load rank";
    private static final String NOT_LOAD_RANK_COMMAND = "";

    @Mock
    private PrintWrapper printWrapper;
    private LoadRankCommandStat underTest;

    @BeforeEach
    public void setUp() {
        underTest = new LoadRankCommandStat(printWrapper);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsExit() {
        // given in setup

        // when
        boolean result = underTest.canProcess(LOAD_RANK_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsNotExit() {
        // given in setup

        // when
        boolean result = underTest.canProcess(NOT_LOAD_RANK_COMMAND);

        // then
        assertFalse(result);
    }

}