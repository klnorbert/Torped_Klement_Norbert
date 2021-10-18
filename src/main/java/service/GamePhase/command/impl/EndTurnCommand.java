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
public class EndTurnCommand implements GameCommand {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(ExitCommand.class);
    private static final String EXIT_COMMAND = "end turn";
    private final GameState gameState;

    /**
     * Constructor
     *
     * @param gameState Game Status
     */
    public EndTurnCommand(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Command interface Override
     *
     * @param input the input as string
     * @return {@code true} if the writed string is "end turn", {@code false} otherwise
     */
    @Override
    public boolean canProcess(String input) {
        return EXIT_COMMAND.equals(input) && !gameState.getPlayer2().isTurnEnd() && !gameState.getPlayer1().isTurnEnd();
    }

    /**
     * End each-player turn
     *
     * Command interface Override
     *
     * @param input the input as string
     */
    @Override
    public void process(String input) {
        LOGGER.info("Performing end turn command");
        if (gameState.isTurn()) {
            gameState.setTurn(false);
            gameState.getPlayer2().setTurnEnd(true);
        } else {
            gameState.setTurn(true);
            gameState.getPlayer1().setTurnEnd(true);
        }
    }

}
