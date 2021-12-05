import model.GameState;
import model.MapVO;
import model.PlayerVO;
import service.command.Command;
import service.command.InputHandler;
import service.game.GameController;
import service.game.GameStepPerformer;
import service.command.impl.*;
import service.command.performer.DeathShipPerformer;
import service.command.performer.HitPerformer;
import service.input.UserInputReader;
import service.map.MapReaderFacade;
import service.ui.MapPrinter;
import service.ui.PrintWrapper;
import service.util.CollectionUtil;
import service.util.MapUtil;
import service.validator.MapValidator;
import service.validator.impl.MapByBoxSizeValidator;
import service.validator.impl.MapByShipBorderValidator;
import service.validator.impl.MapByShipFragmentValidator;
import service.validator.impl.MapValidatorComposer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * <h1>Torpedo Project</h1>
 * This is the Main Method
 *
 * @author Klement Norbert
 */
class Main {

    public static void main(String[] args) {
        GameState gameState = new GameState(null, null, true, false, 10);

        PrintWrapper printWrapper = new PrintWrapper();


        PlayerVO Player1 = new PlayerVO("Achmed", null, null, false);
        PlayerVO Player2 = new PlayerVO("Machmud", null, null, false);
        gameState.setPlayer1(Player1);
        gameState.setPlayer2(Player2);

        int[][] map =
                {{0,0,0,0,0,0,0,0,0},
                {0,4,4,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,4,0,0,0,0,0},
                {0,0,0,4,0,0,4,0,0},
                {0,0,0,0,0,0,4,0,0},
                {4,4,4,0,0,0,4,0,0},
                {0,0,0,0,0,0,0,0,0}};

        int[][] map2 =
                {{0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,4,4,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,0,2,0,0,0,0,0},
                        {0,0,0,4,0,0,4,0,0},
                        {0,0,0,0,0,0,4,0,0},
                        {4,4,4,0,0,0,4,0,0},
                        {0,0,0,0,0,0,0,0,0}};

        CollectionUtil collectionUtil = new CollectionUtil();
        MapUtil mapUtil = new MapUtil();

        List<MapValidator> mapValidatorList = List.of(
                new MapByBoxSizeValidator(gameState),
                new MapByShipBorderValidator(),
                new MapByShipFragmentValidator(mapUtil, gameState.getShipfragment())
        );
        MapValidatorComposer mapValidatorComposer = new MapValidatorComposer(mapValidatorList);



        MapReaderFacade mapReaderFacade = new MapReaderFacade(mapValidatorComposer);
        MapVO mapVO = mapReaderFacade.readMap(new MapVO(9,9,map));
        MapVO mapVO2 = mapReaderFacade.readMap(new MapVO(9,9,map2));
        gameState.getPlayer1().setMapToEmpty(9,9);
        gameState.getPlayer2().setMapToEmpty(9,9);
        gameState.getPlayer1().setCurrentMap(mapVO);
        gameState.getPlayer2().setCurrentMap(mapVO2);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        UserInputReader userInputReader = new UserInputReader(bufferedReader);


        MapPrinter mapPrinter = new MapPrinter(mapUtil,collectionUtil, printWrapper);
        HitPerformer hitPerformer = new HitPerformer();
        DeathShipPerformer deathshipPerformer = new DeathShipPerformer();
        List<Command> commandList = List.of(
                new PrintCommand(gameState, mapPrinter),
                new HitCommand(gameState, collectionUtil, hitPerformer, deathshipPerformer, mapPrinter, printWrapper),
                new ExitCommand(gameState),
                new EndTurnCommand(gameState),
                new HelpCommand(printWrapper),
                new DefaultCommand(printWrapper)
        );
        InputHandler inputHandler = new InputHandler(commandList);

        GameStepPerformer gameStepPerformer = new GameStepPerformer(userInputReader, inputHandler);


        GameController gameController = new GameController(gameState, gameStepPerformer, mapUtil);
        gameController.start();
    }
}