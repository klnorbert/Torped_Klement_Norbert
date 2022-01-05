package torpedo.service.validator.impl;

import java.util.List;

import torpedo.model.MapVO;
import torpedo.service.exception.MapValidationException;
import torpedo.service.validator.MapValidator;

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
