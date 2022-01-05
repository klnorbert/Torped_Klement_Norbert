package torpedo.service.gameperformer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import torpedo.service.command.InputHandler;
import torpedo.service.input.UserInputReader;


/**
 * Unit tests for {@link StepPerformer}.
 */
@ExtendWith(MockitoExtension.class)
public class StepPerformerTest {

    private static final String INPUT = "input";

    @Mock
    private UserInputReader userInputReader;
    @Mock
    private InputHandler inputHandler;

    private StepPerformer underTest;

    @BeforeEach
    public void setUp() {
        underTest = new StepPerformer(userInputReader, inputHandler);
    }

    @Test
    public void testPerformGameStepShouldReadUserInputAndDelegateCallToInputHandler() {
        // given
        given(userInputReader.readInput()).willReturn(INPUT);

        // when
        underTest.performGameStep();

        // then
        verify(userInputReader).readInput();
        verify(inputHandler).handleInput(INPUT);
    }

}
