package torpedo.service.gameperformer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.model.GameState;
import torpedo.service.gameperformer.StepPerformer;

/**
 * Component that controls the flow of a game.
 */
public class ShipPlaceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipPlaceController.class);

    private final GameState gameState;
    private final StepPerformer stepPerformer;

    public ShipPlaceController(GameState gameState, StepPerformer stepPerformer) {
        this.gameState = gameState;
        this.stepPerformer = stepPerformer;
    }

    /**
     * Starts the game loop.
     */
    public void start() {
        LOGGER.info("Starting Ship place loop");
        gameState.getPlayer1().setTurnEnd(true);
        while (isGameInProgress()) {
            stepPerformer.performGameStep();
        }
        LOGGER.info("Ship place loop finished");
    }

    private boolean isGameInProgress() {
        return !gameState.isShouldExit() && gameState.isEmpty();
    }

}
