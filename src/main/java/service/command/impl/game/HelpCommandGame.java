package service.command.impl.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.command.Command;
import service.ui.PrintWrapper;

/**
 * Write every command in Game Phase {@link Command}.
 *
 * @author Klement Norbert
 */
public class HelpCommandGame implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpCommandGame.class);
    private static final String HELP_COMMAND_MESSAGE = "exit: Close the App\n" +
            "print: Print the you map and your hits\n" +
            "hit [A-J] [1-9]: Launce your rocket and make terrorist go Kaboom\n" +
            "end turn: you hand over control to the other player";
    private static final String HELP_COMMAND = "help";
    private final PrintWrapper printWrapper;

    /**
     * Constructor.
     *
     * @param printWrapper Print the error message
     */
    public HelpCommandGame(PrintWrapper printWrapper) {
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
     * @param input "help" input as String
     */
    @Override
    public void process(String input) {
        LOGGER.info("Performing help command");
        printWrapper.printLine(HELP_COMMAND_MESSAGE);
    }

}
