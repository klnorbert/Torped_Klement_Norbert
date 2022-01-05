package torpedo.service.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.model.MapVO;
import torpedo.service.exception.InvalidCodeException;
import torpedo.service.util.CollectionUtil;
import torpedo.service.util.MapUtil;

/**
 * Util class used to print a map.
 *
 * @author Klement Norbert
 */
public class MapPrinter {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(MapPrinter.class);
    private static final String HORIZONTAL_SEPARATOR = "=";
    private static final String VERTICAL_SEPARATOR = " | ";
    private static final String SEPARATOR = "||";
    private static final String SHIP = "O";
    private static final String HIT = "+";
    private static final String DEATH = "W";
    private static final String MISS = "X";
    private static final String EMPTY = " ";
    private final MapUtil mapUtil;
    private final CollectionUtil collectionUtil;
    private final PrintWrapper printWrapper;

    public MapPrinter(MapUtil mapUtil, CollectionUtil collectionUtil, PrintWrapper printWrapper) {
        this.mapUtil = mapUtil;
        this.printWrapper = printWrapper;
        this.collectionUtil = collectionUtil;
    }

    /**
     * Prints the provided map to the standard output.
     */
    public void printMap(MapVO currentMap) {
        LOGGER.info("Printing map to stdout");
        String cordinate = "";
        try {
            for (int columnIndex = 0; columnIndex < currentMap.getNumberOfColumns(); columnIndex++) {
                cordinate = cordinate + collectionUtil.intToCode(columnIndex) + ", ";
            }
        } catch (InvalidCodeException e) {
            LOGGER.error("Error Bad code given Code = {}", cordinate);
            throw new RuntimeException("Error Bad code given");
        }
        printWrapper.printLine("  " + cordinate + SEPARATOR);
        for (int rowIndex = 0; rowIndex < currentMap.getNumberOfRows(); rowIndex++) {
            String rowToPrint = getRowToPrint(currentMap, rowIndex);
            printWrapper.printLine(rowToPrint);
        }
        printWrapper.printLine((getSeparator(HORIZONTAL_SEPARATOR, getSeparatorWidth(currentMap) / 2)));
    }

    /**
     * Prints two provided map to the standard output.
     */
    public void printMap(MapVO currentMap, MapVO enemyMap) {
        LOGGER.info("Printing map to stdout");
        String code = "";
        try {
            for (int columnIndex = 0; columnIndex < currentMap.getNumberOfColumns(); columnIndex++) {
                code = code + collectionUtil.intToCode(columnIndex) + ", ";
            }
        } catch (InvalidCodeException e) {
            LOGGER.error("Error Bad code given Code = {}", code);
            throw new RuntimeException("Error Bad code given");
        }
        printWrapper.printLine("  " + code + SEPARATOR + "   " + code + SEPARATOR);
        for (int rowIndex = 0; rowIndex < currentMap.getNumberOfRows(); rowIndex++) {
            String rowToPrint = getRowToPrint(currentMap, enemyMap, rowIndex);
            printWrapper.printLine(rowToPrint);
        }
        printWrapper.printLine((getSeparator(HORIZONTAL_SEPARATOR, getSeparatorWidth(currentMap))));
    }

    private String getRowToPrint(MapVO current, MapVO enemy, int rowIndex) {
        List<String> row = getRowAsStringList(current, rowIndex);
        List<String> currentMap = joinBoxParts(row);
        row = getRowAsStringList(enemy, rowIndex);
        List<String> enemyMap = joinBoxParts(row);
        int index = rowIndex + 1;
        return index + " " + String.join(", ", currentMap) + ", " + SEPARATOR + " " + index + " " + String.join(", ", enemyMap) + ", " + SEPARATOR;
    }

    private String getRowToPrint(MapVO current, int rowIndex) {
        List<String> row = getRowAsStringList(current, rowIndex);
        List<String> currentMap = joinBoxParts(row);
        int index = rowIndex + 1;
        return index + " " + String.join(", ", currentMap) + ", " + SEPARATOR;
    }

    private List<String> getRowAsStringList(MapVO mapVO, int rowIndex) {
        return mapUtil.getRowOfMap(mapVO, rowIndex).stream()
                .map(this::valueToString)
                .collect(Collectors.toList());
    }

    private List<String> joinBoxParts(List<String> boxPartsList) {
        return boxPartsList.stream()
                .map(strings -> String.join(VERTICAL_SEPARATOR, strings))
                .collect(Collectors.toList());
    }

    private String getSeparator(String separatorCharacter, int times) {
        List<String> separators = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            separators.add(separatorCharacter);
        }
        return String.join("", separators);
    }

    private String valueToString(int value) {
        switch (value) {
            case 0:
                return EMPTY;
            case 1:
                return MISS;
            case 2:
                return HIT;
            case 3:
                return DEATH;
            case 4:
                return SHIP;
            default:
                return String.valueOf(value);
        }
    }

    private int getSeparatorWidth(MapVO mapVO) {
        int numberOfBoxes = mapVO.getNumberOfColumns();
        return (numberOfBoxes * 2) * 3 + 8;
    }

}
