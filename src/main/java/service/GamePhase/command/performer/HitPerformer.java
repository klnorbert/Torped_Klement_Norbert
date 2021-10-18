package service.GamePhase.command.performer;

import model.MapVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.GamePhase.exception.PutException;

/**
 * Helper class used to write a number to a given position of a map.
 */
public class HitPerformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(HitPerformer.class);

    /**
     * Writes a number to a given position into the provided map.
     *
     * A write can only be performed, if there is no fixed number at
     * the requested position.
     *
     * @param mapVO       the map to update
     * @param rowIndex    the index of the row
     * @param columnIndex the index of the column
     */
    public MapVO perform(MapVO mapVO, int rowIndex, int columnIndex) throws PutException {
        LOGGER.info("Performing hit operation with map = {}, rowIndex = {}, columnIndex = {}",
                mapVO, rowIndex, columnIndex);

        int[][] map = mapVO.getMap();


        return new MapVO(mapVO.getNumberOfRows(), mapVO.getNumberOfColumns(), map);
    }

}
