package torpedo.service.validator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.model.MapVO;
import torpedo.service.exception.InvalidShipFragmentException;
import torpedo.service.exception.MapValidationException;
import torpedo.service.validator.MapValidator;

/**
 * Validates the boxes of a map. Each box should contain distinct values.
 */
public class MapByShipFragmentValidator implements MapValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapByShipFragmentValidator.class);
    private final int fragment;

    public MapByShipFragmentValidator(int fragment) {
        this.fragment = fragment;
    }

    @Override
    public void validate(MapVO mapVO) throws MapValidationException {
        int fragment = 0;
        for (int i = 0; i < mapVO.getNumberOfRows(); i++) {
            for (int j = 0; j < mapVO.getNumberOfColumns(); j++) {
                if (mapVO.getMap()[i][j] == 2 || mapVO.getMap()[i][j] == 3 || mapVO.getMap()[i][j] == 4) {
                    fragment++;
                }
            }
        }
        if (fragment != this.fragment) {
            LOGGER.error("Error Invalid ship fragment game_fragment = {}, map_fragment = {}", this.fragment, fragment);
            throw new InvalidShipFragmentException("the proportion of ships is not the same");
        }
    }

}
