package torpedo.service.command.impl.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.model.GameState;
import torpedo.service.command.Command;
import torpedo.service.ui.MapPrinter;

/**
 * Command used to request the printing of the current state
 * of the game map. {@link Command}
 *
 * @author Klement Norbert
 */
public class PrintCommandGame implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintCommandGame.class);
    private static final String PRINT_COMMAND = "print";

    private final GameState gameState;
    private final MapPrinter mapPrinter;

    /**
     * Constructor.
     *
     * @param gameState  Game Status
     * @param mapPrinter Print the error message
     */
    public PrintCommandGame(GameState gameState, MapPrinter mapPrinter) {
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
     * print two map (current and the enemy map).
     * <p>
     * Command interface Override.
     *
     * @param input "print" input as String
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
