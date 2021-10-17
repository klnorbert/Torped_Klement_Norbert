package service.GamePhase.command.impl;

import model.GameState;
import model.MapVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.GamePhase.command.GameCommand;
import service.GamePhase.command.performer.HitPerformer;
import service.GamePhase.exception.PutException;
import service.ui.MapPrinter;
import service.ui.PrintWrapper;

import java.util.regex.Pattern;

/**
 * Command used to check a given field of the map.
 *
 * @author Klement Norbert
 */
public class HitCommand implements GameCommand {
    /**
     * Final!
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HitCommand.class);
    private static final String HIT_COMMAND_REGEX = "^hit [A-J] [1-9]$";
    private static final String HIT_ERROR_MESSAGE = "Can't hit this position";

    private final GameState gameState;
    private final HitPerformer hitPerformer;
    private final MapPrinter mapPrinter;
    private final PrintWrapper printWrapper;

    /**
     * Constructor
     * @param gameState Game status
     * @param hitPerformer Hit commands executor
     * @param mapPrinter Print the torpedo map
     * @param printWrapper I'm lazy to write out the System.out.prinln();
     */
    public HitCommand(GameState gameState, HitPerformer hitPerformer,
                      MapPrinter mapPrinter, PrintWrapper printWrapper) {
        this.gameState = gameState;
        this.hitPerformer = hitPerformer;
        this.mapPrinter = mapPrinter;
        this.printWrapper = printWrapper;
    }

    /**
     * Command interface Override
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
     *
     * Command interface Override
     * @param input a coordinates pl(A1,B4)
     */
    @Override
    public void process(String input) {
        String[] parts = input.split(" ");
        int rowIndex = Integer.parseInt(parts[1]);//unfinished code<------------------------------A B C didn't recognized need to make one void
        int columnIndex = Integer.parseInt(parts[2]);

        LOGGER.info("Performing hit command with rowIndex = {}, columnIndex = {}", rowIndex, columnIndex);
        if (gameState.isTurn()) {
            try {
                MapVO newMap = hitPerformer.perform(gameState.getPlayer1().getEnemyMap(), rowIndex, columnIndex);

                gameState.getPlayer1().setEnemyMap(newMap);
                mapPrinter.printMap(gameState.getPlayer1().getCurrentMap(), newMap);
                gameState.getPlayer1().setTurnEnd(false);
            } catch (PutException e) {
                LOGGER.error("Exception occurred while performing hit operation at the first player", e);
                printWrapper.printLine(HIT_ERROR_MESSAGE);
            }
        } else {
            try {
                MapVO newMap = hitPerformer.perform(gameState.getPlayer2().getEnemyMap(), rowIndex, columnIndex);

                gameState.getPlayer2().setEnemyMap(newMap);
                mapPrinter.printMap(gameState.getPlayer2().getCurrentMap(), newMap);
                gameState.getPlayer2().setTurnEnd(false);
            } catch (PutException e) {
                LOGGER.error("Exception occurred while performing hit operation at the second player", e);
                printWrapper.printLine(HIT_ERROR_MESSAGE);
            }
        }
    }

}
