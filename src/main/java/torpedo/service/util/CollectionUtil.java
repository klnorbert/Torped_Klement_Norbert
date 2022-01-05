package torpedo.service.util;

import torpedo.model.MapVO;
import torpedo.service.exception.InvalidCodeException;

/**
 * Util class containing useful collection related operations.
 */
public class CollectionUtil {
    /**
     * transform code to int.
     *
     * @param code Code pl (A B)
     * @return A = 1, B = 1 ..
     * @throws InvalidCodeException Invalid Code
     */
    public int codeToInt(String code) throws InvalidCodeException {
        switch (code) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
            case "I":
                return 8;
            case "J":
                return 9;
            default: {
                throw new InvalidCodeException("Unexpected value: " + code);
            }
        }
    }

    /**
     * transform int to code.
     *
     * @param code Code pl (1 2)
     * @return 1 = A, 2 = B ..
     * @throws InvalidCodeException Invalid Code
     */
    public String intToCode(int code) throws InvalidCodeException {
        switch (code) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
            case 8:
                return "I";
            case 9:
                return "J";
            default: {
                throw new InvalidCodeException("Unexpected value: " + code);
            }
        }
    }

    /**
     * if the ship is death return true.
     *
     * @param map    Torpedo map
     * @param row    index row
     * @param column index column
     * @return {@code true} if the ship is death, {@code false} otherwise
     */
    public boolean shipDeath(MapVO map, int row, int column) {
        boolean result = true;
        for (int i = 0; row + i != map.getNumberOfRows() + 1 && map.getMap()[row + i][column] != 0 && map.getMap()[row + i][column] != 1; i++) {
            if (map.getMap()[row + i][column] == 4) {
                result = false;
            }
        }
        for (int i = 0; row - i != -1 && map.getMap()[row - i][column] != 0 && map.getMap()[row - i][column] != 1; i++) {
            if (map.getMap()[row - i][column] == 4) {
                result = false;
            }
        }
        for (int i = 0; column + i != map.getNumberOfRows() + 1 && map.getMap()[row][column + i] != 0 && map.getMap()[row][column + i] != 1; i++) {
            if (map.getMap()[row][column + i] == 4) {
                result = false;
            }
        }
        for (int i = 0; column - i != -1 && map.getMap()[row][column - i] != 0 && map.getMap()[row][column - i] != 1; i++) {
            if (map.getMap()[row][column - i] == 4) {
                result = false;
            }
        }
        return result;
    }

}
