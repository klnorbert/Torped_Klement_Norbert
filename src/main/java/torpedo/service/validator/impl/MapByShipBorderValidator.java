package torpedo.service.validator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.model.MapVO;
import torpedo.service.exception.InvalidShipBorderException;
import torpedo.service.exception.MapValidationException;
import torpedo.service.validator.MapValidator;

/**
 * Validates the rows of a map. Each row should contain distinct values.
 */
public class MapByShipBorderValidator implements MapValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapByShipBorderValidator.class);

    public MapByShipBorderValidator() {

    }

    @Override
    public void validate(MapVO mapVO) throws MapValidationException {
        boolean valid = true;
        for (int i = 0; i < mapVO.getNumberOfRows(); i++) {
            for (int j = 0; j < mapVO.getNumberOfColumns(); j++) {
                if (mapVO.getMap()[i][j] == 2 || mapVO.getMap()[i][j] == 3 || mapVO.getMap()[i][j] == 4) {
                    valid = validateShip(mapVO, i, j);
                }
            }
        }
        if (!valid) {
            LOGGER.error("Error Invalid ship placement");
            throw new InvalidShipBorderException("Invalid ship placement you cannot put two ships next to each other.");
        }
    }


    private boolean validateShip(MapVO mapVO, int i, int j) {
        int[][] map = mapVO.getMap();
        int endC = mapVO.getNumberOfColumns();
        int endR = mapVO.getNumberOfRows();
        if (i == 0) {
            if (j == 0 && map[i + 1][j + 1] != 0 && map[i + 1][j + 1] != 1) {
                return false;
            }
            if (j == endC && map[i + 1][j - 1] != 0 && map[i + 1][j - 1] != 1) {
                return false;
            }
            if (j > 0 && j < endC && map[i + 1][j + 1] != 0 && map[i + 1][j + 1] != 1 && map[i + 1][j - 1] != 0 && map[i + 1][j - 1] != 1) {
                return false;
            }
        }
        if (i == endR) {
            if (j == endC && map[i - 1][j - 1] != 0 && map[i - 1][j - 1] != 1) {
                return false;
            }
            if (j == 0 && map[i - 1][j + 1] != 0 && map[i - 1][j + 1] != 1) {
                return false;
            }
            if (j > 0 && j < endC && map[i - 1][j + 1] != 0 && map[i - 1][j + 1] != 1 && map[i - 1][j - 1] != 0 && map[i - 1][j - 1] != 1) {
                return false;
            }
        }
        if (j == 0 && i > 0 && i < endR && map[i + 1][j + 1] != 0 && map[i + 1][j + 1] != 1 && map[i - 1][j + 1] != 0 && map[i - 1][j + 1] != 1) {
            return false;
        }
        if (j == endC && i > 0 && i < endR && map[i + 1][j - 1] != 0 && map[i + 1][j - 1] != 1 && map[i - 1][j - 1] != 0 && map[i - 1][j - 1] != 1) {
            return false;
        }
        if (i > 0 && i < endR && j > 0 && j < endC && map[i + 1][j - 1] != 0 && map[i + 1][j - 1] != 1 && map[i - 1][j - 1] != 0 &&
                map[i - 1][j - 1] != 1 && map[i + 1][j + 1] != 0 && map[i + 1][j + 1] != 1 && map[i - 1][j + 1] != 0 && map[i - 1][j + 1] != 1) {
            return false;
        }
        return true;
    }

}
