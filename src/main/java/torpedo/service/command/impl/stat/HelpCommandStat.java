package torpedo.service.command.impl.stat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.service.command.Command;
import torpedo.service.ui.PrintWrapper;

/**
 * Write every command in Stat Phase. {@link Command}
 *
 * @author Klement Norbert
 */
public class HelpCommandStat implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpCommandStat.class);
    private static final String HELP_COMMAND_MESSAGE =
            "exit: Close the App\n" +
                    "new game: Start a new game with 2 players\n" +
                    "load rank: Load the scoreboard";
    private static final String HELP_COMMAND = "help";
    private final PrintWrapper printWrapper;

    /**
     * Constructor.
     *
     * @param printWrapper Print the error message
     */
    public HelpCommandStat(PrintWrapper printWrapper) {
        this.printWrapper = printWrapper;
    }

    /**
     * Command interface Override.
     *
     * @param input user writes something
     * @return {@code true} if the user wrote the following "help", {@code false} otherwise
     */
    @Override
    public boolean canProcess(String input) {
        return HELP_COMMAND.equals(input);
    }

    /**
     * Print every command with description.
     * <p>
     * Command interface Override.
     *
     * @param input "help" as String
     */
    @Override
    public void process(String input) {
        LOGGER.info("Performing help command");
        printWrapper.printLine(HELP_COMMAND_MESSAGE);
    }

}
