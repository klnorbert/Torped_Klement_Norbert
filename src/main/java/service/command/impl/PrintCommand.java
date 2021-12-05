package service.command.impl;

import model.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.command.Command;
import service.ui.MapPrinter;

/**
 * Command used to request the printing of the current state
 * of the game map.
 */
public class PrintCommand implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintCommand.class);
    private static final String PRINT_COMMAND = "print";

    private final GameState gameState;
    private final MapPrinter mapPrinter;

    /**
     * @param gameState  Game Status
     * @param mapPrinter Print the error message
     */
    public PrintCommand(GameState gameState, MapPrinter mapPrinter) {
        this.gameState = gameState;
        this.mapPrinter = mapPrinter;
    }

    /**
     * Command interface Override
     *
     * @param input the input as string
     * @return {@code true} if the writed string is "print", {@code false} otherwise
     */
    @Override
    public boolean canProcess(String input) {
        return PRINT_COMMAND.equals(input);
    }

    /**
     * print the map
     * <p>
     * Command interface Override
     *
     * @param input the input as string
     */
    @Override
    public void process(String input) {
        if (gameState.isTurn()) {
            LOGGER.info("Performing print command {}", gameState.getPlayer1().getPlayerName());
            mapPrinter.printMap(gameState.getPlayer1().getCurrentMap(), gameState.getPlayer1().getEnemyMap());
        } else {
            LOGGER.info("Performing print command {}", gameState.getPlayer2().getPlayerName());
            mapPrinter.printMap(gameState.getPlayer2().getCurrentMap(), gameState.getPlayer2().getEnemyMap());
        }
    }

}
