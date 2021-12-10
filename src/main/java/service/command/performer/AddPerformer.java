package service.command.performer;

import model.GameState;
import model.MapVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.exception.InvalidWayException;

/**
 * Helper class used to write place a ship on the map.
 */
public class AddPerformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddPerformer.class);

    /**
     * Command interface Override.
     *
     * @param gamestate   State of the Game
     * @param rowIndex    raw index
     * @param columnIndex column index
     * @param code        code pl(A, B, C)
     * @param footage     ship length
     * @return {@code true} if we can place the ship on the map {@code false} otherwise
     */
    public boolean canPerform(GameState gamestate, int rowIndex, int columnIndex, int code, int footage) {
        if (gamestate.isTurn()) {
            if (gamestate.getPlayer1().isTurnEnd()) {
                switch (code) {
                    case 1:
                        return gamestate.getPlayer1().getCurrentMap().getNumberOfRows() >= rowIndex + footage;
                    case 2:
                        return gamestate.getPlayer1().getCurrentMap().getNumberOfColumns() >= columnIndex + footage;
                    case 3:
                        return 0 <= rowIndex - footage;
                    case 4:
                        return 0 <= columnIndex - footage;
                    default:
                        return false;
                }
            }
        } else {
            if (gamestate.getPlayer2().isTurnEnd()) {
                switch (code) {
                    case 1:
                        return gamestate.getPlayer2().getCurrentMap().getNumberOfRows() >= rowIndex + footage;
                    case 2:
                        return gamestate.getPlayer2().getCurrentMap().getNumberOfColumns() >= columnIndex + footage;
                    case 3:
                        return 0 <= rowIndex - footage;
                    case 4:
                        return 0 <= columnIndex - footage;
                    default:
                        return false;
                }
            }
        }
        return false;
    }

    /**
     * Perform the "place".
     *
     * @param mapVO       map
     * @param rowIndex    row index
     * @param columnIndex column index
     * @param way         right, left, up, down as integer
     * @param footage     ship length
     * @return map where the ship placed
     */
    public MapVO perform(MapVO mapVO, int rowIndex, int columnIndex, int way, int footage) throws InvalidWayException {
        LOGGER.debug("Performing Add  rowIndex = {}, columnIndex = {}", rowIndex, columnIndex);
        int[][] map = mapVO.getMap();
        switch (way) {
            case 1: {
                for (int i = 0; i < footage; i++) {
                    map[rowIndex + i][columnIndex] = 4;
                }
                break;
            }
            case 2: {
                for (int i = 0; i < footage; i++) {
                    map[rowIndex][columnIndex + i] = 4;
                }
                break;
            }
            case 3: {
                for (int i = 0; i < footage; i++) {
                    map[rowIndex - i][columnIndex] = 4;
                }
                break;
            }
            case 4: {
                for (int i = 0; i < footage; i++) {
                    map[rowIndex][columnIndex - i] = 4;
                }
                break;
            }
            default: {
                throw new InvalidWayException("Unexpected value: " + way);
            }
        }
        return new MapVO(mapVO.getNumberOfRows(), mapVO.getNumberOfColumns(), map);
    }

}
