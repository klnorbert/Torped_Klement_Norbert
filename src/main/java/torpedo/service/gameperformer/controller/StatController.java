package torpedo.service.gameperformer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.model.GameState;
import torpedo.service.gameperformer.StepPerformer;

/**
 * Component that controls the flow of a game.
 */
public class StatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatController.class);

    private final GameState gameState;
    private final StepPerformer stepPerformer;

    public StatController(GameState gameState, StepPerformer stepPerformer) {
        this.gameState = gameState;
        this.stepPerformer = stepPerformer;
    }

    /**
     * Starts the Stat loop.
     */
    public void start() {
        LOGGER.info("Starting Stat loop");
        gameState.getPlayer1().setTurnEnd(true);
        while (isGameInProgress()) {
            stepPerformer.performGameStep();
        }
        LOGGER.info("Stat loop finished");
    }

    private boolean isGameInProgress() {
        return !gameState.isShouldExit();
    }

}
