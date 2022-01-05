package torpedo.configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import torpedo.model.GameState;
import torpedo.model.PlayerVO;
import torpedo.service.command.InputHandler;
import torpedo.service.command.performer.AddPerformer;
import torpedo.service.command.performer.DeathShipPerformer;
import torpedo.service.command.performer.HitPerformer;
import torpedo.service.gameperformer.StepPerformer;
import torpedo.service.gameperformer.controller.StatController;
import torpedo.service.input.UserInputReader;
import torpedo.service.ui.MapPrinter;
import torpedo.service.ui.PrintWrapper;
import torpedo.service.util.CollectionUtil;
import torpedo.service.util.MapUtil;


/**
 * Spring Java configuration class for generic application related Spring Beans.
 */
@Configuration
public class ApplicationConfiguration {

    @Bean(initMethod = "start")
    StatController statController(GameState gameState, StepPerformer stepPerformer) {
        return new StatController(gameState, stepPerformer);
    }

    @Bean
    GameState gameState() {
        GameState gameState = new GameState(null, null, true);
        PlayerVO player1 = new PlayerVO("", null, null, false);
        PlayerVO player2 = new PlayerVO("", null, null, false);
        gameState.setPlayer1(player1);
        gameState.setPlayer2(player2);

        return gameState;
    }

    @Bean
    StepPerformer stepPerformer(UserInputReader userInputReader, InputHandler inputHandler) {
        return new StepPerformer(userInputReader, inputHandler);
    }

    @Bean
    UserInputReader userInputReader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return new UserInputReader(bufferedReader);
    }

    @Bean
    MapPrinter mapPrinter(MapUtil mapUtil, CollectionUtil collectionUtil, PrintWrapper printWrapper) {
        return new MapPrinter(mapUtil, collectionUtil, printWrapper);
    }

    @Bean
    HitPerformer hitPerformer() {
        return new HitPerformer();
    }

    @Bean
    DeathShipPerformer deathShipPerformer() {
        return new DeathShipPerformer();
    }

    @Bean
    AddPerformer addPerformer() {
        return new AddPerformer();
    }

    @Bean
    PrintWrapper printWrapper() {
        return new PrintWrapper();
    }

}
