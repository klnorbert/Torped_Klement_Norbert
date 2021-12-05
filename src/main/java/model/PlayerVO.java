package model;

import java.util.Objects;

/**
 * Model class used to represent one player data.
 *
 * @author Klement Norbert
 */
public class PlayerVO {

    private final String PlayerName;
    private MapVO currentMap;
    private MapVO enemyMap;
    private boolean turnEnd;

    /**
     * Constructor
     *
     * @param playerName Player Name
     * @param currentMap Player1 map (You see the ship)
     * @param enemyMap   Player2 map (You didn't see the ship)
     * @param turnEnd    {@code true} if you have a step, {@code false} otherwise
     */
    public PlayerVO(String playerName, MapVO currentMap, MapVO enemyMap, boolean turnEnd) {
        PlayerName = playerName;
        this.currentMap = currentMap;
        this.enemyMap = enemyMap;
        this.turnEnd = turnEnd;
    }


    //Setter

    public void setCurrentMap(MapVO currentMap) {
        this.currentMap = currentMap;
    }

    public void setEnemyMap(MapVO enemyMap) {
        this.enemyMap = enemyMap;
    }

    public void setTurnEnd(boolean turnEnd) {
        this.turnEnd = turnEnd;
    }


    //Getter

    public boolean isTurnEnd() {
        return turnEnd;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public MapVO getCurrentMap() {
        return currentMap;
    }

    public MapVO getEnemyMap() {
        return enemyMap;
    }

    //Other Method

    public void setMapToEmpty(int row,int column){
        int[][] result = new int[row][column];
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                result[i][j]=0;
            }
        }
        setCurrentMap(new MapVO(row,column,result));
        setEnemyMap(new MapVO(row,column,result));
    }
    /**
     * Override the normal equals method
     *
     * @param o Data
     * @return {@code true} if the o parameters is equals with the (numberOfRows,numberOfColumns,map), {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerVO playerVO = (PlayerVO) o;
        return Objects.equals(PlayerName, playerVO.PlayerName) &&
                Objects.equals(currentMap, playerVO.currentMap) &&
                Objects.equals(enemyMap, playerVO.enemyMap);
    }

    /**
     * Override Normal hashcode
     *
     * @return class hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(PlayerName, currentMap, enemyMap);
    }

    /**
     * Override Normal toString
     *
     * @return every data on this class
     */
    @Override
    public String toString() {
        return "PlayerVO{" +
                "PlayerName='" + PlayerName + '\'' +
                ", currentMap=" + currentMap +
                ", enemyMap=" + enemyMap +
                '}';
    }
}
