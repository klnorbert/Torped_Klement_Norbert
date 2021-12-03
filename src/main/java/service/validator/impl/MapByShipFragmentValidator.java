package service.validator.impl;

import model.MapVO;
import service.exception.InvalidShipFragmentException;
import service.exception.MapValidationException;
import service.util.MapUtil;
import service.validator.MapValidator;

/**
 * Validates the boxes of a map. Each box should contain distinct values.
 */
public class MapByShipFragmentValidator implements MapValidator {

    private final int fragment;

    public MapByShipFragmentValidator(MapUtil mapUtil, int fragment) {
        this.fragment = fragment;
    }

    @Override
    public void validate(MapVO mapVO) throws MapValidationException {
        int fragment=0;
        for (int i = 0; i < mapVO.getNumberOfRows(); i++) {
            for (int j = 0; j < mapVO.getNumberOfColumns(); i++) {
                if(mapVO.getMap()[i][j]==2 || mapVO.getMap()[i][j]==3 || mapVO.getMap()[i][j]==4){
                    fragment++;
                }
            }
        }
        if (fragment != this.fragment) {
            throw new InvalidShipFragmentException("the proportion of ships is not the same");
        }
    }

}
