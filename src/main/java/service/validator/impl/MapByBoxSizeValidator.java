package service.validator.impl;

import model.GameState;
import model.MapVO;
import service.exception.InvalidBoxSizeException;
import service.exception.MapValidationException;
import service.validator.MapValidator;


/**
 * Validates the boxes of a map. Each box should contain distinct values.
 */
public class MapByBoxSizeValidator implements MapValidator {

    private final GameState gameState;

    public MapByBoxSizeValidator(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void validate(MapVO mapVO) throws MapValidationException {
        if(gameState.getPlayer1().getCurrentMap().getNumberOfColumns()!=gameState.getPlayer2().getEnemyMap().getNumberOfColumns()){
            throw new InvalidBoxSizeException("Invalid box size!");
        }
        if(gameState.getPlayer1().getCurrentMap().getNumberOfRows()!=gameState.getPlayer2().getEnemyMap().getNumberOfRows()){
            throw new InvalidBoxSizeException("Invalid box size!");
        }
        if(gameState.getPlayer1().getCurrentMap().getNumberOfColumns()!=gameState.getPlayer2().getEnemyMap().getNumberOfRows()){
            throw new InvalidBoxSizeException("Invalid box size!");
        }
    }



}
