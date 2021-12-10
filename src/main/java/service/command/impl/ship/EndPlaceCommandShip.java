package service.command.impl.ship;

import model.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.command.Command;
import service.ui.PrintWrapper;

/**
 * Exit the Ship Phase. {@link Command}
 *
 * @author Klement Norbert
 */
public class EndPlaceCommandShip implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(EndPlaceCommandShip.class);
    private static final String END_PLACE_COMMAND_REGEX = "end place";

    private final GameState gameState;
    private final PrintWrapper printWrapper;


    /**
     * Constructor.
     *
     * @param gameState    Game status
     * @param printWrapper print the error
     */
    public EndPlaceCommandShip(GameState gameState, PrintWrapper printWrapper) {
        this.gameState = gameState;
        this.printWrapper = printWrapper;
    }

    /**
     * Command interface Override.
     *
     * @param input user writes something
     * @return {@code true} if the user wrote the following "end place", {@code false} otherwise
     */
    @Override
    public boolean canProcess(String input) {
        return END_PLACE_COMMAND_REGEX.equals(input) && !gameState.getPlayer2().isTurnEnd() && !gameState.getPlayer1().isTurnEnd() && !gameState.isShipPlace();
    }

    /**
     * Exit the Ship Phase.
     * <p>
     * Command interface Override.
     *
     * @param input "end place" as String
     */
    @Override
    public void process(String input) {
        LOGGER.info("Performing end place command");
        printWrapper.cleanConsol();
        gameState.setEmpty(false);
    }
}
