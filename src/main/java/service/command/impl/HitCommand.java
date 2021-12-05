package service.command.impl;

import model.GameState;
import model.MapVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.command.Command;
import service.command.performer.DeathShipPerformer;
import service.command.performer.HitPerformer;
import service.exception.InvalidHitException;
import service.ui.MapPrinter;
import service.ui.PrintWrapper;
import service.util.CollectionUtil;

import java.util.regex.Pattern;

/**
 * Command used to check a given field of the map.
 *
 * @author Klement Norbert
 */
public class HitCommand implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(HitCommand.class);
    private static final String HIT_COMMAND_REGEX = "^hit [A-J] [1-9]$";
    private static final String HIT_ERROR_MESSAGE = "Can't hit this position";

    private final GameState gameState;
    private final HitPerformer hitPerformer;
    private final MapPrinter mapPrinter;
    private final PrintWrapper printWrapper;
    private final CollectionUtil collectionUtil;
    private final DeathShipPerformer DeathShipPerformer;
    /**
     * Constructor
     *
     * @param gameState    Game status
     * @param hitPerformer Hit commands executor
     * @param mapPrinter   Print the torpedo map
     * @param printWrapper I'm lazy to write out the System.out.prinln();
     */
    public HitCommand(GameState gameState, CollectionUtil collectionUtil, HitPerformer hitPerformer,
            DeathShipPerformer DeathShipPerformer, MapPrinter mapPrinter, PrintWrapper printWrapper) {
        this.gameState = gameState;
        this.hitPerformer = hitPerformer;
        this.mapPrinter = mapPrinter;
        this.printWrapper = printWrapper;
        this.collectionUtil = collectionUtil;
        this.DeathShipPerformer = DeathShipPerformer;
    }

    /**
     * Command interface Override
     *
     * @param input a coordinates pl(A1,B4)
     * @return {@code true} if it meets the expectation, {@code false} otherwise
     */
    @Override
    public boolean canProcess(String input) {
        return Pattern.matches(HIT_COMMAND_REGEX, input);
    }

    /**
     * the "hit" (faith xD) command sends an atom to the given coordinates
     * And start the HitPerformer
     * <p>
     * Command interface Override
     *
     * @param input a coordinates pl(A1,B4)
     */
    @Override
    public void process(String input) {
        try {
            String[] parts = input.split(" ");
            int columnIndex = collectionUtil.CordinateToInt(parts[1]);
            int rowIndex = Integer.parseInt(parts[2])-1;


            if (hitPerformer.canPerform(gameState, rowIndex, columnIndex)) {
                LOGGER.info("Performing hit command with rowIndex = {}, columnIndex = {}", rowIndex, columnIndex);
                if (gameState.isTurn()) {

                    MapVO newMap2 = hitPerformer.Perform(gameState.getPlayer1().getEnemyMap(),
                            gameState.getPlayer2().getCurrentMap(), rowIndex, columnIndex, collectionUtil, DeathShipPerformer);

                    gameState.getPlayer1().setEnemyMap(newMap2);

                    mapPrinter.printMap(gameState.getPlayer1().getCurrentMap(), gameState.getPlayer1().getEnemyMap());
                    gameState.getPlayer1().setTurnEnd(false);

                }
                if(!gameState.isTurn()){
                    MapVO newMap2 = hitPerformer.Perform(gameState.getPlayer2().getEnemyMap(),
                            gameState.getPlayer1().getCurrentMap(), rowIndex, columnIndex, collectionUtil, DeathShipPerformer);

                    gameState.getPlayer2().setEnemyMap(newMap2);
                    mapPrinter.printMap(gameState.getPlayer2().getCurrentMap(), gameState.getPlayer2().getEnemyMap());
                    gameState.getPlayer2().setTurnEnd(false);
                }
            }
        } catch (InvalidHitException e) {
            LOGGER.error("Exception occurred while performing hit operation at the player", e);
            printWrapper.printLine(HIT_ERROR_MESSAGE);
        }
    }
}
