package torpedo.service.command.impl.stat;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.model.GameState;
import torpedo.service.command.Command;
import torpedo.service.command.InputHandler;
import torpedo.service.command.impl.game.DefaultCommandGame;
import torpedo.service.command.impl.game.EndTurnCommandGame;
import torpedo.service.command.impl.game.ExitCommandGame;
import torpedo.service.command.impl.game.HelpCommandGame;
import torpedo.service.command.impl.game.HitCommandGame;
import torpedo.service.command.impl.game.PrintCommandGame;
import torpedo.service.command.impl.ship.AddCommandShip;
import torpedo.service.command.impl.ship.DefaultCommandShip;
import torpedo.service.command.impl.ship.EndPlaceCommandShip;
import torpedo.service.command.impl.ship.EndTurnCommandShip;
import torpedo.service.command.impl.ship.ExitCommandShip;
import torpedo.service.command.impl.ship.HelpCommandShip;
import torpedo.service.command.impl.ship.PlaceCommandShip;
import torpedo.service.command.impl.ship.PrintCommandShip;
import torpedo.service.command.performer.AddPerformer;
import torpedo.service.command.performer.DeathShipPerformer;
import torpedo.service.command.performer.HitPerformer;
import torpedo.service.gameperformer.StepPerformer;
import torpedo.service.gameperformer.controller.GameController;
import torpedo.service.gameperformer.controller.ShipPlaceController;
import torpedo.service.input.UserInputReader;
import torpedo.service.ui.MapPrinter;
import torpedo.service.ui.PrintWrapper;
import torpedo.service.util.CollectionUtil;
import torpedo.service.util.MapUtil;

/**
 * Create a new game with 2 player. {@link Command}
 *
 * @author Klement Norbert
 */
public class NewGameCommandStat implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(NewGameCommandStat.class);
    private static final String NEW_GAME_COMMAND_REGEX = "new game";

    private final GameState gameState;
    private final HitPerformer hitPerformer;
    private final AddPerformer addPerformer;
    private final MapPrinter mapPrinter;
    private final PrintWrapper printWrapper;
    private final CollectionUtil collectionUtil;
    private final DeathShipPerformer deathShipPerformer;
    private final MapUtil mapUtil;
    private final UserInputReader userInputReader;

    /**
     * Constructor.
     *
     * @param gameState    Game status
     * @param hitPerformer Hit commands executor
     * @param mapPrinter   Print the torpedo map
     * @param printWrapper I'm lazy to write out the System.out.prinln();
     */
    public NewGameCommandStat(GameState gameState, CollectionUtil collectionUtil, HitPerformer hitPerformer, AddPerformer addPerformer,
                              DeathShipPerformer deathShipPerformer, MapPrinter mapPrinter, PrintWrapper printWrapper, MapUtil mapUtil,
                              UserInputReader userInputReader) {
        this.gameState = gameState;
        this.hitPerformer = hitPerformer;
        this.addPerformer = addPerformer;
        this.mapPrinter = mapPrinter;
        this.printWrapper = printWrapper;
        this.collectionUtil = collectionUtil;
        this.deathShipPerformer = deathShipPerformer;
        this.mapUtil = mapUtil;
        this.userInputReader = userInputReader;
    }

    /**
     * Command interface Override.
     *
     * @param input user writes something
     * @return {@code true} if the user wrote the following "new game" {@code false} otherwise
     */
    @Override
    public boolean canProcess(String input) {
        return NEW_GAME_COMMAND_REGEX.equals(input);
    }

    /**
     * Start the Ship Phase loop and then the Game Phase loop.
     * <p>
     * Command interface Override.
     *
     * @param input "new game" as String
     */
    @Override
    public void process(String input) {
        LOGGER.info("Performing new game command");
        LOGGER.debug("Player1 Player 2 clean the current map and the enemy map");
        gameState.getPlayer1().setMapToEmpty(9, 9);
        gameState.getPlayer2().setMapToEmpty(9, 9);
        gameState.setEmpty(true);
        gameState.setShipFragment(0);
        printWrapper.printLine("Player1 Name: ");
        gameState.getPlayer1().setPlayerName(userInputReader.readInput());
        printWrapper.printLine("Player2 Name: ");
        gameState.getPlayer2().setPlayerName(userInputReader.readInput());

        List<Command> shipCommandList = List.of(
                new EndPlaceCommandShip(gameState, printWrapper),
                new PlaceCommandShip(gameState, collectionUtil, addPerformer, mapPrinter, printWrapper),
                new AddCommandShip(gameState, collectionUtil, addPerformer, mapPrinter, printWrapper),
                new ExitCommandShip(gameState),
                new EndTurnCommandShip(gameState, printWrapper),
                new HelpCommandShip(printWrapper),
                new PrintCommandShip(gameState, mapPrinter),
                new DefaultCommandShip(printWrapper)
        );
        InputHandler shipInputHandler = new InputHandler(shipCommandList);

        StepPerformer shipStepPerformer = new StepPerformer(userInputReader, shipInputHandler);
        LOGGER.info("Start Ship Place controller");
        ShipPlaceController shipController = new ShipPlaceController(gameState, shipStepPerformer);
        shipController.start();

        gameState.getPlayer1().setEnemyMapToEmpty(
                gameState.getPlayer1().getCurrentMap().getNumberOfRows(), gameState.getPlayer1().getCurrentMap().getNumberOfColumns());
        gameState.getPlayer2().setEnemyMapToEmpty(
                gameState.getPlayer1().getCurrentMap().getNumberOfRows(), gameState.getPlayer1().getCurrentMap().getNumberOfColumns());
        List<Command> gameCommandList = List.of(
                new PrintCommandGame(gameState, mapPrinter),
                new HitCommandGame(gameState, collectionUtil, hitPerformer, deathShipPerformer, mapPrinter, printWrapper),
                new ExitCommandGame(gameState),
                new EndTurnCommandGame(gameState, printWrapper),
                new HelpCommandGame(printWrapper),
                new DefaultCommandGame(printWrapper)
        );
        InputHandler gameInputHandler = new InputHandler(gameCommandList);

        StepPerformer gameStepPerformer = new StepPerformer(userInputReader, gameInputHandler);
        LOGGER.info("Start Game controller");
        GameController gamecontroller = new GameController(gameState, gameStepPerformer, mapUtil, printWrapper);
        gamecontroller.start();

    }
}
