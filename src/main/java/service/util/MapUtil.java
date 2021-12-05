package service.util;

import model.GameState;
import model.MapVO;

import java.util.ArrayList;
import java.util.List;

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


    public boolean isMapCompleted(GameState game) {
        int[][] map1 = game.getPlayer1().getCurrentMap().getMap();
        int[][] map2 = game.getPlayer2().getCurrentMap().getMap();
        int row= game.getPlayer1().getCurrentMap().getNumberOfRows();
        int column= game.getPlayer1().getCurrentMap().getNumberOfColumns();
        int player1=0;
        int player2=0;
        for(int i=0;i<row;i++){
            for (int j=0;j<column;j++){
                if(map1[i][j]==3){
                    player1++;
                }
                if(map2[i][j]==3){
                    player2++;
                }
            }
        }
        return game.getShipfragment() == player1 || game.getShipfragment() == player2;
    }
}
