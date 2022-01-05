package torpedo.service.gameperformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.service.command.InputHandler;
import torpedo.service.input.UserInputReader;

/**
 * Component that performs a game step.
 */
public class StepPerformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(StepPerformer.class);

    private final UserInputReader userInputReader;
    private final InputHandler inputHandler;

    public StepPerformer(UserInputReader userInputReader, InputHandler inputHandler) {
        this.userInputReader = userInputReader;
        this.inputHandler = inputHandler;
    }

    /**
     * Performs a game step.
     * <p>
     * A game step consists of taking the input from the user, then handling
     * the input.
     */
    public void performGameStep() {
        String input = userInputReader.readInput();
        LOGGER.info("Read user input = '{}'", input);
        inputHandler.handleInput(input);
    }

}
