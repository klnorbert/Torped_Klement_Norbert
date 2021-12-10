package service.command.impl.ship;

import model.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.command.Command;
import service.ui.MapPrinter;

/**
 * Command used to request the printing of the current state
 * of the Ship place map. {@link Command}
 */
public class PrintCommandShip implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintCommandShip.class);
    private static final String PRINT_COMMAND = "print";

    private final GameState gameState;
    private final MapPrinter mapPrinter;

    /**
     * Constructor.
     *
     * @param gameState  Game Status
     * @param mapPrinter Print the error message and the map
     */
    public PrintCommandShip(GameState gameState, MapPrinter mapPrinter) {
        this.gameState = gameState;
        this.mapPrinter = mapPrinter;
    }

    /**
     * Command interface Override.
     *
     * @param input user writes something
     * @return {@code true} if the user wrote the following "print", {@code false} otherwise
     */
    @Override
    public boolean canProcess(String input) {
        return PRINT_COMMAND.equals(input);
    }

    /**
     * Print a map.
     * <p>
     * Command interface Override.
     *
     * @param input "print" as String
     */
    @Override
    public void process(String input) {
        if (gameState.isTurn()) {
            LOGGER.info("Performing print command player: {}", gameState.getPlayer1().getPlayerName());
            mapPrinter.printMap(gameState.getPlayer1().getCurrentMap());
        } else {
            LOGGER.info("Performing print command player: {}", gameState.getPlayer2().getPlayerName());
            mapPrinter.printMap(gameState.getPlayer2().getCurrentMap());
        }
    }

}
