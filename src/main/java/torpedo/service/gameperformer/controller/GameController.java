package torpedo.service.gameperformer.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.model.GameState;
import torpedo.service.gameperformer.StepPerformer;
import torpedo.service.ui.PrintWrapper;
import torpedo.service.util.MapUtil;

/**
 * Component that controls the flow of a game.
 */
public class GameController {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
    private final GameState gameState;
    private final StepPerformer stepPerformer;
    private final MapUtil mapUtil;
    private final PrintWrapper printWrapper;

    public GameController(GameState gameState, StepPerformer stepPerformer, MapUtil mapUtil, PrintWrapper printWrapper) {
        this.gameState = gameState;
        this.stepPerformer = stepPerformer;
        this.mapUtil = mapUtil;
        this.printWrapper = printWrapper;
    }

    /**
     * Starts the game loop.
     */
    public void start() {
        LOGGER.info("Starting game loop");
        gameState.getPlayer1().setTurnEnd(true);
        gameState.setTurn(true);
        while (isGameInProgress()) {
            stepPerformer.performGameStep();
        }
        LOGGER.info("Game loop finished");
        try {
            if (!gameState.isShouldExit()) {
                Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/./progtech_rank", "progtech_user", "progtech_user");
                Statement statement = connection.createStatement();
                String winner = "";
                if (!mapUtil.isMapCompleted(
                        gameState.getPlayer1().getCurrentMap().getMap(),
                        gameState.getPlayer1().getCurrentMap().getNumberOfRows(),
                        gameState.getPlayer1().getCurrentMap().getNumberOfColumns(),
                        gameState.getShipFragment()
                )) {
                    winner = gameState.getPlayer1().getPlayerName();
                    printWrapper.printLine("Winner is " + gameState.getPlayer1().getPlayerName());
                } else {
                    winner = gameState.getPlayer2().getPlayerName();
                    printWrapper.printLine("Winner is " + gameState.getPlayer2().getPlayerName());
                }
                String sql = "SELECT * FROM USERS WHERE USER_NAME = '" + winner + "';";
                ResultSet resultSet = statement.executeQuery(sql);
                boolean playerData = false;
                int winCount = 0;
                while (resultSet.next()) {
                    playerData = true;
                    winCount = resultSet.getInt("WIN_COUNT") + 1;
                }
                if (playerData) {
                    LOGGER.info("Update Win count");
                    sql = "UPDATE USERS SET WIN_COUNT='" + winCount + "' WHERE USER_NAME= '" + winner + "';";
                } else {
                    LOGGER.info("Insert User Name and Win count");
                    sql = "INSERT INTO USERS (USER_NAME, WIN_COUNT) VALUES ('" + winner + "', 1);";
                }
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            LOGGER.error("Database SQL ERROR");
        }
    }

    private boolean isGameInProgress() {
        return !gameState.isShouldExit() &&
                !mapUtil.isMapCompleted(
                        gameState.getPlayer1().getCurrentMap().getMap(),
                        gameState.getPlayer1().getCurrentMap().getNumberOfRows(),
                        gameState.getPlayer1().getCurrentMap().getNumberOfColumns(),
                        gameState.getShipFragment()
                ) &&
                !mapUtil.isMapCompleted(
                        gameState.getPlayer2().getCurrentMap().getMap(),
                        gameState.getPlayer2().getCurrentMap().getNumberOfRows(),
                        gameState.getPlayer2().getCurrentMap().getNumberOfColumns(),
                        gameState.getShipFragment()
                );
    }

}
