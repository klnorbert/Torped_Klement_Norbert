package service.validator.impl;

import model.MapVO;
import service.exception.MapValidationException;
import service.validator.MapValidator;

import java.util.List;

/**
 * Ties multiple {@link MapValidator}s together and validates a map through them.
 */
public class MapValidatorComposer implements MapValidator {

    private final List<MapValidator> mapValidatorList;

    public MapValidatorComposer(List<MapValidator> mapValidatorList) {
        this.mapValidatorList = mapValidatorList;
    }

    @Override
    public void validate(MapVO mapVO) throws MapValidationException {
        for (MapValidator mapValidator : mapValidatorList) {
            mapValidator.validate(mapVO);
        }
    }

}
