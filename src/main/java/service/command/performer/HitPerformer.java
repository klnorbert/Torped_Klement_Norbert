package service.command.performer;

import model.GameState;
import model.MapVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.exception.InvalidHitException;
import service.util.CollectionUtil;

/**
 * Helper class used to write a number to a given position of a map.
 */
public class HitPerformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(HitPerformer.class);

    public boolean canPerform(GameState gamestate, int rowIndex, int columnIndex){
        if(gamestate.isTurn()){
            if(gamestate.getPlayer1().isTurnEnd()){
                return gamestate.getPlayer1().getEnemyMap().getMap()[rowIndex][columnIndex] == 0;
            }
        }
        else {
            if(gamestate.getPlayer2().isTurnEnd()){
                return gamestate.getPlayer2().getEnemyMap().getMap()[rowIndex][columnIndex] == 0;
            }
        }
        return false;
    }
    /**
     * Writes a number to a given position into the provided map.
     *
     * A write can only be performed, if there is no fixed number at
     * the requested position.
     *
     * @param change       the map to update
     * @param scan       the map to scan
     * @param rowIndex    the index of the row
     * @param columnIndex the index of the column
     */
    public MapVO Perform(MapVO change, MapVO scan, int rowIndex, int columnIndex, CollectionUtil collectionUtil, DeathShipPerformer DeathShipPerformer) throws InvalidHitException{
        LOGGER.info("Performing hit operation with map = {}, rowIndex = {}, columnIndex = {}",
                change, rowIndex, columnIndex);

        int[][] map = change.getMap();
        int[][] map2 = scan.getMap();
        MapVO ma = change;
        if(map2[rowIndex][columnIndex]==0){
            map[rowIndex][columnIndex]=1;
        }
        if(map2[rowIndex][columnIndex]==4){
            map[rowIndex][columnIndex]=2;
            map2[rowIndex][columnIndex]=2;
            if(collectionUtil.shipDeath(new MapVO(ma.getNumberOfRows(), ma.getNumberOfColumns(), map2),rowIndex,columnIndex)){
                ma= DeathShipPerformer.perform(new MapVO(ma.getNumberOfRows(), ma.getNumberOfColumns(), map),rowIndex,columnIndex);
                DeathShipPerformer.perform(new MapVO(ma.getNumberOfRows(), ma.getNumberOfColumns(), map2),rowIndex,columnIndex);
                map2[rowIndex][columnIndex]=3;
                ma.getMap()[rowIndex][columnIndex]=3;
                return ma;
            }
        }

        return new MapVO(ma.getNumberOfRows(), ma.getNumberOfColumns(), map);
    }

}
