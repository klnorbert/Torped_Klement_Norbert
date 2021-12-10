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
 * place a ship the given field of the map. {@link Command}
 *
 * @author Klement Norbert
 */
public class PlaceCommandShip implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceCommandShip.class);
    private static final String PLACE_COMMAND_REGEX = "^place [A-J] [1-9] [1-4] [2-5]$";
    private static final String PLACE_ERROR_MESSAGE = "Can't place this position";

    private final GameState gameState;
    private final AddPerformer addPerformer;
    private final MapPrinter mapPrinter;
    private final PrintWrapper printWrapper;
    private final CollectionUtil collectionUtil;

    /**
     * Constructor.
     *
     * @param gameState    Game status
     * @param mapPrinter   Print the torpedo map
     * @param printWrapper I'm lazy to write out the System.out.prinln();
     */
    public PlaceCommandShip(GameState gameState, CollectionUtil collectionUtil, AddPerformer addPerformer,
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
     * @return {@code true} if the user wrote the following pl(place A 1 2 2,place B 4 4 4), {@code false} otherwise
     */
    @Override
    public boolean canProcess(String input) {
        return Pattern.matches(PLACE_COMMAND_REGEX, input) && !gameState.isShipPlace();
    }

    /**
     * place a ship on the map (you can change the size).
     * <p>
     * Command interface Override.
     *
     * @param input some coordinates pl(place A 1 2 2,place B 4 4 4) as String
     */
    @Override
    public void process(String input) {
        String[] parts = input.split(" ");
        try {
            int columnIndex = collectionUtil.codeToInt(parts[1]);
            int rowIndex = Integer.parseInt(parts[2]) - 1;
            int way = Integer.parseInt(parts[3]);
            int footage = Integer.parseInt(parts[4]);
            if (addPerformer.canPerform(gameState, rowIndex, columnIndex, way, footage)) {
                gameState.setShipFootage(footage);
                gameState.setShipFragment(gameState.getShipFragment() + footage);
                MapValidatorPerformer mapValidatorPerformer = new MapValidatorPerformer(gameState);
                if (gameState.isTurn()) {
                    LOGGER.info("Performing Place command with rowIndex = {}, columnIndex = {}, way = {}, footage = {}, PlayerName = {}",
                            rowIndex, way, footage, columnIndex, gameState.getPlayer1().getPlayerName());
                    MapVO mapVO = addPerformer.perform(gameState.getPlayer1().getCurrentMap(), rowIndex, columnIndex, way, footage);
                    mapValidatorPerformer.readMap(mapVO);
                    gameState.getPlayer1().setCurrentMap(mapVO);
                    mapPrinter.printMap(gameState.getPlayer1().getCurrentMap());
                    gameState.getPlayer1().setTurnEnd(false);

                }
                if (!gameState.isTurn()) {
                    LOGGER.info("Performing Place command with rowIndex = {}, columnIndex = {}, way = {}, footage = {}, PlayerName = {}",
                            rowIndex, columnIndex, way, footage, gameState.getPlayer2().getPlayerName());
                    MapVO mapVO = addPerformer.perform(gameState.getPlayer2().getCurrentMap(), rowIndex, columnIndex, way, footage);
                    mapValidatorPerformer.readMap(mapVO);
                    gameState.getPlayer2().setCurrentMap(mapVO);
                    mapPrinter.printMap(gameState.getPlayer2().getCurrentMap());
                    gameState.getPlayer2().setTurnEnd(false);
                }
                gameState.setShipPlace(true);
            } else {
                printWrapper.printLine(PLACE_ERROR_MESSAGE);
                LOGGER.warn("Warning Performing hit command with rowIndex = {}, columnIndex = {}", rowIndex, columnIndex);
            }
        } catch (InvalidCodeException e) {
            LOGGER.error("Error Bad code given Code = {}", parts[2]);
            throw new RuntimeException("Error Bad code given");
        } catch (InvalidWayException e) {
            LOGGER.error("Error Bad code given Way = {}", parts[3]);
            throw new RuntimeException("Error Bad code given Way");
        }
    }
}
