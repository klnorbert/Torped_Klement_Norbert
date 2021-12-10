package service.command.impl.game;

import model.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.command.Command;

/**
 * Command used to exit from the game. {@link Command}
 *
 * @author Klement Norbert
 */
public class ExitCommandGame implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(ExitCommandGame.class);
    private static final String EXIT_COMMAND = "exit";
    private final GameState gameState;

    /**
     * Constructor.
     *
     * @param gameState Game Status
     */
    public ExitCommandGame(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Command interface Override.
     *
     * @param input user writes something
     * @return {@code true} if the user wrote the following "exit", {@code false} otherwise
     */
    @Override
    public boolean canProcess(String input) {
        return EXIT_COMMAND.equals(input);
    }

    /**
     * Exit The Game.
     * <p>
     * Command interface Override.
     *
     * @param input "exit" input as String
     */
    @Override
    public void process(String input) {
        LOGGER.info("Performing exit command");
        gameState.setShouldExit(true);
    }

}
