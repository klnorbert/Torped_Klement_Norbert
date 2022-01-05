package torpedo.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import torpedo.model.GameState;
import torpedo.service.validator.MapValidator;
import torpedo.service.validator.impl.MapByBoxSizeValidator;
import torpedo.service.validator.impl.MapByShipBorderValidator;
import torpedo.service.validator.impl.MapByShipFragmentValidator;
import torpedo.service.validator.impl.MapValidatorComposer;

/**
 * Spring Java configuration class for Sudoku table validation specific Spring Beans.
 */
@Configuration
public class ValidatorConfiguration {

    @Bean
    MapValidatorComposer mapValidatorComposer(List<MapValidator> mapValidatorList) {
        return new MapValidatorComposer(mapValidatorList);
    }

    @Bean
    MapValidator mapByShipFragmentValidator(GameState gameState) {
        return new MapByShipFragmentValidator(gameState.getShipFragment());
    }

    @Bean
    MapValidator mapByShipBorderValidator() {
        return new MapByShipBorderValidator();
    }

    @Bean
    MapValidator mapByBoxSizeValidator(GameState gameState) {
        return new MapByBoxSizeValidator(gameState);
    }

}
