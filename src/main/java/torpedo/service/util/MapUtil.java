package torpedo.service.util;

import java.util.ArrayList;
import java.util.List;

import torpedo.model.MapVO;

/**
 * Util class that helps to extract given parts of a {@link MapVO} object.
 */
public class MapUtil {

    /**
     * Returns all the numbers from a chosen row as a list.
     *
     * @param mapVO    the map
     * @param rowIndex the index of the chosen row
     * @return a list of integers
     */
    public List<Integer> getRowOfMap(MapVO mapVO, int rowIndex) {
        List<Integer> result = new ArrayList<>();

        int[][] map = mapVO.getMap();
        for (int i = 0; i < mapVO.getNumberOfColumns(); i++) {
            result.add(map[rowIndex][i]);
        }
        return result;
    }

    /**
     * Returns all the numbers from a chosen column as a list.
     *
     * @param mapVO       the map
     * @param columnIndex the index of the column
     * @return a list of integers
     */
    public List<Integer> getColumnOfMap(MapVO mapVO, int columnIndex) {
        List<Integer> result = new ArrayList<>();

        int[][] map = mapVO.getMap();
        for (int i = 0; i < mapVO.getNumberOfRows(); i++) {
            result.add(map[i][columnIndex]);
        }

        return result;
    }

    /**
     * check map status.
     *
     * @param map      torpedo map
     * @param row      index of row
     * @param column   index of column
     * @param fragment how many fragment have on the torpedo map
     * @return {@code true} if no one ship fragment is alive, {@code false} otherwise
     */
    public boolean isMapCompleted(int[][] map, int row, int column, int fragment) {
        int player = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (map[i][j] == 3) {
                    player++;
                }
            }
        }
        return fragment == player;
    }
}
