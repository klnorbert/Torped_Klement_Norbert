package service.GamePhase.command.impl;

import model.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.GamePhase.command.GameCommand;

/**
 * Command used to exit from the game.
 *
 * @author Klement Norbert
 */
public class ExitCommand implements GameCommand {
    /**
     * Final!
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExitCommand.class);
    private static final String EXIT_COMMAND = "exit";
    private final GameState gameState;

    /**
     * Constructor
     * @param gameState Game Status
     */
    public ExitCommand(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Command interface Override
     * @param input the input as string
     * @return {@code true} if the writed string is "exit", {@code false} otherwise
     */
    @Override
    public boolean canProcess(String input) {
        return EXIT_COMMAND.equals(input);
    }

    /**
     * Exit The Game
     *
     * Command interface Override
     * @param input the input as string
     */
    @Override
    public void process(String input) {
        LOGGER.info("Performing exit command");
        gameState.setShouldExit(true);
    }

}
