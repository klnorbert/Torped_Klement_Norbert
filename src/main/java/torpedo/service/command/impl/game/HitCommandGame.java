package torpedo.service.command.impl.game;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.model.GameState;
import torpedo.model.MapVO;
import torpedo.service.command.Command;
import torpedo.service.command.performer.DeathShipPerformer;
import torpedo.service.command.performer.HitPerformer;
import torpedo.service.exception.InvalidCodeException;
import torpedo.service.ui.MapPrinter;
import torpedo.service.ui.PrintWrapper;
import torpedo.service.util.CollectionUtil;

/**
 * Command used to hit a given field of the map. {@link Command}
 *
 * @author Klement Norbert
 */
public class HitCommandGame implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(HitCommandGame.class);
    private static final String HIT_COMMAND_REGEX = "^hit [A-J] [1-9]$";
    private static final String HIT_ERROR_MESSAGE = "Can't hit this position";

    private final GameState gameState;
    private final HitPerformer hitPerformer;
    private final MapPrinter mapPrinter;
    private final PrintWrapper printWrapper;
    private final CollectionUtil collectionUtil;
    private final DeathShipPerformer deathShipPerformer;


    /**
     * Constructor.
     *
     * @param gameState          Game status
     * @param collectionUtil     help to convert the code to int and otherwise
     * @param hitPerformer       Hit commands executor
     * @param deathShipPerformer help to perform the dead ship
     * @param mapPrinter         Print the torpedo map
     * @param printWrapper       I'm lazy to write out the System.out.prinln();
     */
    public HitCommandGame(GameState gameState, CollectionUtil collectionUtil, HitPerformer hitPerformer,
                          DeathShipPerformer deathShipPerformer, MapPrinter mapPrinter, PrintWrapper printWrapper) {
        this.gameState = gameState;
        this.hitPerformer = hitPerformer;
        this.mapPrinter = mapPrinter;
        this.printWrapper = printWrapper;
        this.collectionUtil = collectionUtil;
        this.deathShipPerformer = deathShipPerformer;
    }

    /**
     * Command interface Override.
     *
     * @param input user writes something
     * @return {@code true} if the user wrote the following pl(hit A 1,hit B 4), {@code false} otherwise
     */
    @Override
    public boolean canProcess(String input) {
        return Pattern.matches(HIT_COMMAND_REGEX, input);
    }

    /**
     * the "hit" (faith xD) command sends an atom to the given coordinates
     * And start the HitPerformer.
     * <p>
     * Command interface Override.
     *
     * @param input a coordinates pl(hit A 1,hit B 4) as String
     */
    @Override
    public void process(String input) {
        String[] parts = input.split(" ");
        try {
            int columnIndex = collectionUtil.codeToInt(parts[1]);
            int rowIndex = Integer.parseInt(parts[2]) - 1;


            if (hitPerformer.canPerform(gameState, rowIndex, columnIndex)) {
                if (gameState.isTurn()) {
                    LOGGER.info("Performing hit command with rowIndex = {}, columnIndex = {}, PlayerName = {}",
                            rowIndex, columnIndex, gameState.getPlayer1().getPlayerName());
                    MapVO newMap2 = hitPerformer.perform(gameState.getPlayer1().getEnemyMap(),
                            gameState.getPlayer2().getCurrentMap(), rowIndex, columnIndex, collectionUtil, deathShipPerformer);

                    gameState.getPlayer1().setEnemyMap(newMap2);

                    mapPrinter.printMap(gameState.getPlayer1().getCurrentMap(), gameState.getPlayer1().getEnemyMap());
                    gameState.getPlayer1().setTurnEnd(false);

                }
                if (!gameState.isTurn()) {
                    LOGGER.info("Performing hit command with rowIndex = {}, columnIndex = {}, PlayerName = {}",
                            rowIndex, columnIndex, gameState.getPlayer1().getPlayerName());
                    MapVO newMap2 = hitPerformer.perform(gameState.getPlayer2().getEnemyMap(),
                            gameState.getPlayer1().getCurrentMap(), rowIndex, columnIndex, collectionUtil, deathShipPerformer);

                    gameState.getPlayer2().setEnemyMap(newMap2);
                    mapPrinter.printMap(gameState.getPlayer2().getCurrentMap(), gameState.getPlayer2().getEnemyMap());
                    gameState.getPlayer2().setTurnEnd(false);
                }
            } else {
                printWrapper.printLine(HIT_ERROR_MESSAGE);
                LOGGER.warn("Warning Performing hit command with rowIndex = {}, columnIndex = {}", rowIndex, columnIndex);
            }
        } catch (InvalidCodeException e) {
            LOGGER.error("Error Bad code given Code = {}", parts[1]);
            throw new RuntimeException("Error Bad code given");
        }
    }
}
