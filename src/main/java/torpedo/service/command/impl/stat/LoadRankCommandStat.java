package torpedo.service.command.impl.stat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.service.command.Command;
import torpedo.service.ui.PrintWrapper;

/**
 * Create a new game with 2 player. {@link Command}
 *
 * @author Klement Norbert
 */
public class LoadRankCommandStat implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadRankCommandStat.class);
    private static final String LOAD_RANK_COMMAND_REGEX = "load rank";
    static final String SELECT_STATEMENT = "SELECT * FROM USERS;";

    private final PrintWrapper printWrapper;


    /**
     * Constructor.
     *
     * @param printWrapper I'm lazy to write out the System.out.prinln();
     */
    public LoadRankCommandStat(PrintWrapper printWrapper) {
        this.printWrapper = printWrapper;
    }

    /**
     * Command interface Override.
     *
     * @param input user writes something
     * @return {@code true} if the user wrote the following "new game" {@code false} otherwise
     */
    @Override
    public boolean canProcess(String input) {
        return LOAD_RANK_COMMAND_REGEX.equals(input);
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
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/./progtech_rank", "progtech_user", "progtech_user");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_STATEMENT);
            printWrapper.printLine("Data:");
            while (resultSet.next()) {
                String name = resultSet.getString("USER_NAME");
                int winCount = resultSet.getInt("WIN_COUNT");

                printWrapper.printLine(name + ": " + winCount);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
