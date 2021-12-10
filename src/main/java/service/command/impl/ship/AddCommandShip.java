package service.command.impl.ship;

import java.util.regex.Pattern;

import model.GameState;
import model.MapVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.command.Command;
import service.command.performer.AddPerformer;
import service.exception.InvalidCodeException;
import service.exception.InvalidWayException;
import service.ui.MapPrinter;
import service.ui.PrintWrapper;
import service.util.CollectionUtil;
import service.validator.MapValidatorPerformer;

/**
 * Command used to send a ship a given coordinate.
 * the ship length is locked by the other player. {@link Command}
 *
 * @author Klement Norbert
 */
public class AddCommandShip implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(AddCommandShip.class);
    private static final String ADD_COMMAND_REGEX = "^add [A-J] [1-9] [1-4]$";
    private static final String ADD_ERROR_MESSAGE = "Can't add this position";

    private final GameState gameState;
    private final AddPerformer addPerformer;
    private final MapPrinter mapPrinter;
    private final PrintWrapper printWrapper;
    private final CollectionUtil collectionUtil;


    /**
     * Constructor.
     *
     * @param gameState      Game status
     * @param collectionUtil some help command to convert Code to Int and otherwise
     * @param addPerformer   Perform add command
     * @param mapPrinter     Print the torpedo map
     * @param printWrapper   I'm lazy to write out the System.out.prinln();
     */
    public AddCommandShip(GameState gameState, CollectionUtil collectionUtil, AddPerformer addPerformer,
                          MapPrinter mapPrinter, PrintWrapper printWrapper) {
        this.gameState = gameState;
        this.addPerformer = addPerformer;
        this.mapPrinter = mapPrinter;
        this.printWrapper = printWrapper;
        this.collectionUtil = collectionUtil;
    }

    /**
     * Command interface Override.
     *
     * @param input user writes something
     * @return {@code true} if the user wrote the following pl(add A 1 1,add B 4 3), {@code false} otherwise
     */
    @Override
    public boolean canProcess(String input) {
        return Pattern.matches(ADD_COMMAND_REGEX, input) && gameState.isShipPlace()
                && (gameState.getPlayer2().isTurnEnd() || gameState.getPlayer1().isTurnEnd());
    }

    /**
     * the "add" command sends a ship to the given coordinates.
     * And start the AddPerformer.
     * <p>
     * Command interface Override.
     *
     * @param input a coordinates pl(add A 1 1,add B 4 3) as String
     */
    @Override
    public void process(String input) {
        String[] parts = input.split(" ");
        int way = Integer.parseInt(parts[3]);
        try {
            int columnIndex = collectionUtil.codeToInt(parts[1]);
            int rowIndex = Integer.parseInt(parts[2]) - 1;
            int footage = gameState.getShipFootage();

            MapValidatorPerformer mapValidatorPerformer = new MapValidatorPerformer(gameState);
            if (addPerformer.canPerform(gameState, rowIndex, columnIndex, way, footage)) {
                if (gameState.isTurn()) {
                    LOGGER.info("Performing add command with rowIndex = {}, columnIndex = {}, way = {}, footage = {}, PlayerName = {}",
                            rowIndex, way, footage, columnIndex, gameState.getPlayer1().getPlayerName());
                    MapVO mapVO = addPerformer.perform(gameState.getPlayer1().getCurrentMap(), rowIndex, columnIndex, way, footage);
                    mapValidatorPerformer.readMap(mapVO);
                    gameState.getPlayer1().setCurrentMap(mapVO);
                    mapPrinter.printMap(gameState.getPlayer1().getCurrentMap());
                    gameState.getPlayer1().setTurnEnd(false);

                }
                if (!gameState.isTurn()) {
                    LOGGER.info("Performing add command with rowIndex = {}, columnIndex = {}, way = {}, footage = {}, PlayerName = {}",
                            rowIndex, way, footage, columnIndex, gameState.getPlayer1().getPlayerName());
                    MapVO mapVO = addPerformer.perform(gameState.getPlayer2().getCurrentMap(), rowIndex, columnIndex, way, footage);
                    mapValidatorPerformer.readMap(mapVO);
                    gameState.getPlayer2().setCurrentMap(mapVO);
                    mapPrinter.printMap(gameState.getPlayer2().getCurrentMap());
                    gameState.getPlayer2().setTurnEnd(false);
                }
                gameState.setShipPlace(false);
            } else {
                printWrapper.printLine(ADD_ERROR_MESSAGE);
                LOGGER.warn("Warning Performing hit command with rowIndex = {}, columnIndex = {}", rowIndex, columnIndex);
            }
        } catch (InvalidCodeException e) {
            LOGGER.error("Error Bad code given Code = {}", parts[2]);
            throw new RuntimeException("Error Bad code given");
        } catch (InvalidWayException e) {
            LOGGER.error("Error Bad code given Way = {}", way);
            throw new RuntimeException("Error Bad code given Way");
        }
    }
}
