package model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Model class used to represent a Torpedo map.
 *
 * @author Klement Norbert
 */
public class MapVO {
    /**
     * Final!
     */
    private final int numberOfRows;
    private final int numberOfColumns;
    private final int[][] map;

    /**
     * @param numberOfRows Rows Length
     * @param numberOfColumns Columns Length
     * @param map Int[][] variable
     */
    public MapVO(int numberOfRows, int numberOfColumns, int[][] map) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.map = map;
    }

    /**
     * GETTER METHOD
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int[][] getMap() {
        return map;
    }

    /**
     * Override the normal equals method
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
        MapVO mapBasic = (MapVO) o;
        return numberOfRows == mapBasic.numberOfRows &&
                numberOfColumns == mapBasic.numberOfColumns && Arrays.equals(map, mapBasic.map);
    }

    /**
     * Override Normal hashcode
     * @return class hashcode
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(numberOfRows, numberOfColumns);
        result = 31 * result + Arrays.hashCode(map);
        return result;
    }

    /**
     * Override Normal toString
     * @return every data on this class
     */
    @Override
    public String toString() {
        return "MapBasic{" +
                "numberOfRows=" + numberOfRows +
                ", numberOfColumns=" + numberOfColumns +
                ", map=" + Arrays.toString(map) +
                '}';
    }

    /**
     * Get the data and returns the data to be copied
     *
     * @param array int[][] data
     * @return copy every data in array to this int[][]
     */
    private int[][] deepCopy(int[][] array) {
        int[][] result = null;

        if (array != null) {
            result = new int[array.length][];
            for (int i = 0; i < array.length; i++) {
                result[i] = Arrays.copyOf(array[i], array[i].length);
            }
        }
        return result;
    }
}
