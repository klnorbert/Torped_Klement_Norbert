package service.command.performer;

import model.GameState;
import model.MapVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.util.CollectionUtil;

/**
 * Helper class used to hit to a given position of a map.
 */
public class HitPerformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(HitPerformer.class);

    /**
     * If the given coordinate is had undamaged object.
     *
     * @param gamestate State of the Game
     * @param rowIndex row index
     * @param columnIndex column index
     * @return {@code true} if the user wrote the following pl(hit A 1 2,hit B 4), {@code false} otherwise
     */
    public boolean canPerform(GameState gamestate, int rowIndex, int columnIndex) {
        if (gamestate.isTurn()) {
            if (gamestate.getPlayer1().isTurnEnd()) {
                return gamestate.getPlayer1().getEnemyMap().getMap()[rowIndex][columnIndex] == 0;
            }
        } else {
            if (gamestate.getPlayer2().isTurnEnd()) {
                return gamestate.getPlayer2().getEnemyMap().getMap()[rowIndex][columnIndex] == 0;
            }
        }
        return false;
    }

    /**
     * Hit on the map if we find 0 coordinate change 1 (4 to 2).
     *
     * @param change      the map to update
     * @param scan        the map to scan
     * @param rowIndex    the index of the row
     * @param columnIndex the index of the column
     * @param collectionUtil other method
     * @param deathShipPerformer other method to help to kill some ship
     * @return map where the kamehameha is landed
     */
    public MapVO perform(MapVO change, MapVO scan, int rowIndex, int columnIndex, CollectionUtil collectionUtil, DeathShipPerformer deathShipPerformer) {
        LOGGER.info("Performing Hit  rowIndex = {}, columnIndex = {}", rowIndex, columnIndex);

        int[][] map = change.getMap();
        int[][] map2 = scan.getMap();
        MapVO ma = change;
        if (map2[rowIndex][columnIndex] == 0) {
            map[rowIndex][columnIndex] = 1;
            map2[rowIndex][columnIndex] = 1;
        }
        if (map2[rowIndex][columnIndex] == 4) {
            map[rowIndex][columnIndex] = 2;
            map2[rowIndex][columnIndex] = 2;
            if (collectionUtil.shipDeath(new MapVO(ma.getNumberOfRows(), ma.getNumberOfColumns(), map2), rowIndex, columnIndex)) {
                ma = deathShipPerformer.perform(new MapVO(ma.getNumberOfRows(), ma.getNumberOfColumns(), map), rowIndex, columnIndex);
                deathShipPerformer.perform(new MapVO(ma.getNumberOfRows(), ma.getNumberOfColumns(), map2), rowIndex, columnIndex);
                map2[rowIndex][columnIndex] = 3;
                ma.getMap()[rowIndex][columnIndex] = 3;
                return ma;
            }
        }

        return new MapVO(ma.getNumberOfRows(), ma.getNumberOfColumns(), map);
    }

}
