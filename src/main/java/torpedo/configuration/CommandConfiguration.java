package torpedo.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import torpedo.model.GameState;
import torpedo.service.command.Command;
import torpedo.service.command.InputHandler;
import torpedo.service.command.impl.stat.DefaultCommandStat;
import torpedo.service.command.impl.stat.ExitCommandStat;
import torpedo.service.command.impl.stat.HelpCommandStat;
import torpedo.service.command.impl.stat.LoadRankCommandStat;
import torpedo.service.command.impl.stat.NewGameCommandStat;
import torpedo.service.command.performer.AddPerformer;
import torpedo.service.command.performer.DeathShipPerformer;
import torpedo.service.command.performer.HitPerformer;
import torpedo.service.input.UserInputReader;
import torpedo.service.ui.MapPrinter;
import torpedo.service.ui.PrintWrapper;
import torpedo.service.util.CollectionUtil;
import torpedo.service.util.MapUtil;

/**
 * Spring Java configuration class for command specific Spring Beans.
 */
@Configuration
public class CommandConfiguration {

    @Bean
    InputHandler inputHandler(List<Command> commandList) {
        return new InputHandler(commandList);
    }

    @Bean
    ExitCommandStat exitCommandStat(GameState gameState) {
        return new ExitCommandStat(gameState);
    }

    @Bean
    NewGameCommandStat newGameCommandStat(GameState gameState, CollectionUtil collectionUtil, HitPerformer hitPerformer, AddPerformer addPerformer,
                                          DeathShipPerformer deathShipPerformer, MapPrinter mapPrinter, PrintWrapper printWrapper, MapUtil mapUtil,
                                          UserInputReader userInputReader) {
        return new NewGameCommandStat(gameState, collectionUtil, hitPerformer,
                addPerformer, deathShipPerformer, mapPrinter, printWrapper, mapUtil, userInputReader);
    }


    @Bean
    LoadRankCommandStat loadCommand(PrintWrapper printWrapper) {
        return new LoadRankCommandStat(printWrapper);
    }

    @Bean
    HelpCommandStat helpCommandStat(PrintWrapper printWrapper) {
        return new HelpCommandStat(printWrapper);
    }

    @Bean
    DefaultCommandStat defaultCommandStat(PrintWrapper printWrapper) {
        return new DefaultCommandStat(printWrapper);
    }

}
