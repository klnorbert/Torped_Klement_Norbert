package service.command.impl.game;

import model.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.command.Command;
import service.ui.PrintWrapper;

/**
 * Command used to end the turn each-player from the game. {@link Command}
 *
 * @author Klement Norbert
 */
public class EndTurnCommandGame implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(EndTurnCommandGame.class);
    private static final String EXIT_COMMAND = "end turn";
    private final GameState gameState;
    private final PrintWrapper printWrapper;

    /**
     * Constructor.
     *
     * @param gameState    Game Status
     * @param printWrapper print line
     */
    public EndTurnCommandGame(GameState gameState, PrintWrapper printWrapper) {
        this.gameState = gameState;
        this.printWrapper = printWrapper;
    }

    /**
     * Command interface Override.
     *
     * @param input user writes something
     * @return {@code true} if the user wrote the following "end turn", {@code false} otherwise
     */
    @Override
    public boolean canProcess(String input) {
        return EXIT_COMMAND.equals(input) && !gameState.getPlayer2().isTurnEnd() && !gameState.getPlayer1().isTurnEnd();
    }

    /**
     * End each-player turn.
     * <p>
     * Command interface Override.
     *
     * @param input "end turn" as String
     */
    @Override
    public void process(String input) {
        LOGGER.info("Performing end turn command");
        printWrapper.cleanConsol();
        if (gameState.isTurn()) {
            gameState.setTurn(false);
            gameState.getPlayer2().setTurnEnd(true);
        } else {
            gameState.setTurn(true);
            gameState.getPlayer1().setTurnEnd(true);
        }
    }

}
