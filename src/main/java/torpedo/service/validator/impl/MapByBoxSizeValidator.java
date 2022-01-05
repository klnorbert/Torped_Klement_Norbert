package torpedo.service.validator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.model.GameState;
import torpedo.model.MapVO;
import torpedo.service.exception.InvalidBoxSizeException;
import torpedo.service.exception.MapValidationException;
import torpedo.service.validator.MapValidator;


/**
 * Validates the boxes of a map. Each box should contain distinct values.
 */
public class MapByBoxSizeValidator implements MapValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapByBoxSizeValidator.class);
    private final GameState gameState;

    public MapByBoxSizeValidator(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void validate(MapVO mapVO) throws MapValidationException {
        if (gameState.getPlayer1().getCurrentMap().getNumberOfColumns() != gameState.getPlayer2().getEnemyMap().getNumberOfColumns()) {
            LOGGER.error("Error when check map size");
            throw new InvalidBoxSizeException("Invalid box size!");
        }
        if (gameState.getPlayer1().getCurrentMap().getNumberOfRows() != gameState.getPlayer2().getEnemyMap().getNumberOfRows()) {
            LOGGER.error("Error when check map size");
            throw new InvalidBoxSizeException("Invalid box size!");
        }
        if (gameState.getPlayer1().getCurrentMap().getNumberOfColumns() != gameState.getPlayer2().getEnemyMap().getNumberOfRows()) {
            LOGGER.error("Error when check map size");
            throw new InvalidBoxSizeException("Invalid box size!");
        }
    }


}
