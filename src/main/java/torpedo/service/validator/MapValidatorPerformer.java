package torpedo.service.validator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.model.GameState;
import torpedo.model.MapVO;
import torpedo.service.exception.MapValidationException;
import torpedo.service.validator.impl.MapByBoxSizeValidator;
import torpedo.service.validator.impl.MapByShipBorderValidator;
import torpedo.service.validator.impl.MapByShipFragmentValidator;
import torpedo.service.validator.impl.MapValidatorComposer;

/**
 * Facade that makes it easier to read a map.
 */
public class MapValidatorPerformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapValidatorPerformer.class);

    private final MapValidator mapValidator;

    public MapValidatorPerformer(GameState gameState) {
        List<MapValidator> mapValidatorList = List.of(
                new MapByBoxSizeValidator(gameState),
                new MapByShipBorderValidator(),
                new MapByShipFragmentValidator(gameState.getShipFragment())
        );
        this.mapValidator = new MapValidatorComposer(mapValidatorList);
    }

    /**
     * Validate map.
     *
     * @param mapVO torpedo map
     * @return {@code true} if the map is valid ,{@code false} otherwise
     */
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