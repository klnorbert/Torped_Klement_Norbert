package service.map;

import model.MapVO;
import service.exception.MapValidationException;
import service.validator.MapValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Facade that makes it easier to read a map.
 */
public class MapReaderFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapReaderFacade.class);

    private final MapValidator mapValidator;

    public MapReaderFacade(MapValidator mapValidator) {
        this.mapValidator = mapValidator;
    }


    public MapVO readMap(MapVO mapVO) {
        try {
            mapValidator.validate(mapVO);

            return mapVO;
        } catch (MapValidationException e) {
            LOGGER.error("Failed to validate map", e);
            throw new RuntimeException("The loaded map was invalid");
        }
    }

}